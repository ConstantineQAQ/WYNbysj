package com.constantineqaq.service.grpc.impl;

import com.constantineqaq.base.pojo.Person;
import com.constantineqaq.dal.manager.PersonManager;
import com.constantineqaq.grpc.person.*;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.Resource;
import net.devh.boot.grpc.server.service.GrpcService;



@GrpcService
public class PersonServiceImpl extends PersonServiceGrpc.PersonServiceImplBase {

    @Resource
    private PersonManager personManager;

    @Override
    public void getAllPerson(Empty request, StreamObserver<PersonResponse> responseObserver) {
        personManager.getAllPerson();
    }

    @Override
    public void getPersonById(PersonRequest request, StreamObserver<PersonResponse> responseObserver) {
        personManager.getPersonById(request.getId());
    }

    @Override
    public void addPerson(PersonG request, StreamObserver<CommonResponse> responseObserver) {
        personManager.addPerson(Person.convertToLocalPerson(request));
    }

    @Override
    public void updatePerson(PersonG request, StreamObserver<CommonResponse> responseObserver) {
        personManager.updatePerson(Person.convertToLocalPerson(request));
    }

    @Override
    public void deletePersonById(PersonRequest request, StreamObserver<CommonResponse> responseObserver) {
        personManager.deletePersonById(request.getId());
    }

}
