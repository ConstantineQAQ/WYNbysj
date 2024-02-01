package com.constantineqaq.service;

import com.constantineqaq.base.enums.CommonEnum;
import com.constantineqaq.base.exception.ResourceNotFoundException;
import com.constantineqaq.base.entity.Person;
import com.constantineqaq.grpc.person.*;
import com.google.protobuf.Empty;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyaning33
 */
@Service
@Slf4j
public class PersonService {

    @GrpcClient("backend-server")
    private PersonServiceGrpc.PersonServiceBlockingStub personServiceBlockingStub;

    public List<Person> getAllPerson() {
        List<Person> personList = new ArrayList<>();
        PersonResponse allPerson = personServiceBlockingStub.getAllPerson(Empty.newBuilder().build());
        CommonResponse response = allPerson.getCommonResponse();
        if (response.getCode() != CommonEnum.SUCCESS.getCode()) {
            log.error("getAllPerson error, code: {}, message: {}", response.getCode(), response.getMessage());
            return personList;
        }
        allPerson.getPersonList().forEach(person -> {
            Person localPerson = Person.convertToLocalPerson(person);
            personList.add(localPerson);
            log.info("person: {}", localPerson);
        });
        return personList;
    }

    public Person getPersonById(Integer id) {
        PersonResponse personById = personServiceBlockingStub.getPersonById(PersonRequest.newBuilder()
                        .setId(id)
                .build());
        CommonResponse response = personById.getCommonResponse();
        if (response.getCode() != CommonEnum.SUCCESS.getCode()) {
            log.error("getPersonById error code: {}, message: {}", response.getCode(), response.getMessage());
            throw new ResourceNotFoundException("没有此人员信息");
        }
        return Person.convertToLocalPerson(personById.getPerson(0));
    }

    public void addPerson(Person person) {
        PersonG personG = PersonG.newBuilder()
                .setName(person.getName())
                .setAge(person.getAge())
                .setGender(person.getGender())
                .build();
        CommonResponse response = personServiceBlockingStub.addPerson(personG);
        if (response.getCode() != CommonEnum.SUCCESS.getCode()) {
            log.error("addPerson error, code: {}, message: {}", response.getCode(), response.getMessage());
        }
    }
}
