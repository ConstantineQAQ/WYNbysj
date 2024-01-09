package com.constantineqaq.dal.manager;


import com.constantineqaq.base.dto.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface PersonManager {

    List<Person> getAllPerson();

    Person getPersonById(int id);

    void addPerson(Person person);

    void updatePerson(Person person);

    void deletePersonById(int id);
}
