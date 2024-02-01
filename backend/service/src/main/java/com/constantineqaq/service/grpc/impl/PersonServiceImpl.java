package com.constantineqaq.service.grpc.impl;

import com.constantineqaq.base.enums.CommonEnum;
import com.constantineqaq.dal.mapper.PersonMapper;
import com.constantineqaq.base.entity.Person;
import com.constantineqaq.grpc.person.*;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

import net.devh.boot.grpc.server.service.GrpcService;

import javax.annotation.Resource;
import java.util.List;


@GrpcService
public class PersonServiceImpl extends PersonServiceGrpc.PersonServiceImplBase {

    @Resource
    private PersonMapper personMapper;

    @Override
    public void getAllPerson(Empty request, StreamObserver<PersonResponse> responseObserver) {
        List<Person> personList = personMapper.selectList(null);
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
        Person person = personMapper.selectById(request.getId());
        PersonResponse.Builder builder = PersonResponse.newBuilder();
        // 如果没有找到对应的person，返回失败
        if (person == null) {
            builder.setCommonResponse(CommonResponse.newBuilder()
                    .setCode(CommonEnum.FAIL.getCode())
                    .setMessage(CommonEnum.FAIL.getMessage())
                    .build());
            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();
            return;
        }
        builder.addPerson(PersonG.newBuilder()
                .setId(person.getId())
                .setName(person.getName())
                .setAge(person.getAge())
                .setGender(person.getGender())
                .build());
        builder.setCommonResponse(CommonResponse.newBuilder()
                .setCode(CommonEnum.SUCCESS.getCode())
                .setMessage(CommonEnum.SUCCESS.getMessage())
                .build());
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void addPerson(PersonG request, StreamObserver<CommonResponse> responseObserver) {
        personMapper.insert(Person.convertToLocalPerson(request));
        CommonResponse response = CommonResponse.newBuilder()
                .setCode(CommonEnum.SUCCESS.getCode())
                .setMessage(CommonEnum.SUCCESS.getMessage())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
