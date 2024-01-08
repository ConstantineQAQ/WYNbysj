package com.constantineqaq.base.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student_main")
public class Person {
    @TableId
    private Integer id;
    private String name;
    private Integer age;
    private String gender;

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