package com.constantineqaq.base.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.constantineqaq.grpc.person.PersonG;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@TableName("student_main")
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("gender")
    private Integer gender;

    public static Person convertToLocalPerson(PersonG personG) {
        Person person = new Person();
        person.setId(personG.getId());
        person.setName(personG.getName());
        person.setAge(personG.getAge());
        person.setGender(personG.getGender());
        return person;
    }
}