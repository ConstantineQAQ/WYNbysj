package com.constantineqaq.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.constantineqaq.base.dto.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


import java.util.List;

@Mapper
@Component
public interface PersonDao {

    List<Person> getAllPerson();

    Person getPersonById(int id);

    void addPerson(Person person);

    void updatePerson(Person person);

    void deletePersonById(int id);
}
