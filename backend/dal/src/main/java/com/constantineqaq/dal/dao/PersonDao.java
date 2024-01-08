package com.constantineqaq.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.constantineqaq.base.pojo.Person;

import java.util.List;

public interface PersonDao extends BaseMapper<Person> {

    List<Person> getAllPerson();

    Person getPersonById(int id);

    void addPerson(Person person);

    void updatePerson(Person person);

    void deletePersonById(int id);
}
