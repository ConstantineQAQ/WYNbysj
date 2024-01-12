package com.constantineqaq.web.grpc;

import com.constantineqaq.base.dto.Person;
import com.constantineqaq.grpc.person.PersonResponse;
import com.constantineqaq.grpc.person.PersonServiceGrpc;
import com.google.protobuf.Empty;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PersonService {

    @GrpcClient("backend-server")
    private PersonServiceGrpc.PersonServiceBlockingStub personServiceBlockingStub;

    public List<Person> getAllPerson() {
        List<Person> personList = new ArrayList<>();
        PersonResponse allPerson = personServiceBlockingStub.getAllPerson(Empty.newBuilder().build());
        allPerson.getPersonList().forEach(person -> {
            Person localPerson = Person.convertToLocalPerson(person);
            personList.add(localPerson);
            log.info("person: {}", localPerson);
        });
        return personList;
    }
}
