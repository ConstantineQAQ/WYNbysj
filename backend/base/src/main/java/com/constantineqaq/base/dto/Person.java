package com.constantineqaq.base.dto;


import lombok.Data;

@Data
public class Person {
    private Integer id;
    private String name;
    private Integer age;
    private Integer gender;

    // gRPC类转化为POJO类
    public static Person convertToLocalPerson(com.constantineqaq.grpc.person.PersonG personG) {
        Person person = new Person();
        person.setId(personG.getId());
        person.setName(personG.getName());
        person.setAge(personG.getAge());
        person.setGender(personG.getGender());
        return person;
    }
}
