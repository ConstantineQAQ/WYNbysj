package com.constantineqaq.dal.pojo;


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
    private Integer gender;
}