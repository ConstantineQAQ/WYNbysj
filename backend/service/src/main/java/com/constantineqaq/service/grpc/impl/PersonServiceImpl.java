package com.constantineqaq.service.grpc.impl;

import com.constantineqaq.base.dto.Person;
import com.constantineqaq.dal.manager.PersonManager;
import com.constantineqaq.grpc.person.*;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.Resource;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;


@GrpcService
public class PersonServiceImpl extends PersonServiceGrpc.PersonServiceImplBase {

    @Resource
    private PersonManager personManager;

    @Override
    public void getAllPerson(Empty request, StreamObserver<PersonResponse> responseObserver) {
        List<Person> personList = personManager.getAllPerson();
        PersonResponse.Builder builder = PersonResponse.newBuilder();

        for (Person person : personList) {
            builder.addPerson(PersonG.newBuilder()
                    .setId(person.getId())
                    .setName(person.getName())
                    .setAge(person.getAge())
                    .setGender(person.getGender())
                    .build());
        }
        builder.setCommonResponse(CommonResponse.newBuilder()
                .setCode(200)
                .setMessage("success")
                .build());
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getPersonById(PersonRequest request, StreamObserver<PersonResponse> responseObserver) {
        personManager.getPersonById(request.getId());
    }

    @Override
    public void addPerson(PersonG request, StreamObserver<CommonResponse> responseObserver) {
        personManager.addPerson(com.constantineqaq.base.dto.Person.convertToLocalPerson(request));
    }

    @Override
    public void updatePerson(PersonG request, StreamObserver<CommonResponse> responseObserver) {
        personManager.updatePerson(com.constantineqaq.base.dto.Person.convertToLocalPerson(request));
    }

    @Override
    public void deletePersonById(PersonRequest request, StreamObserver<CommonResponse> responseObserver) {
        personManager.deletePersonById(request.getId());
    }

}
