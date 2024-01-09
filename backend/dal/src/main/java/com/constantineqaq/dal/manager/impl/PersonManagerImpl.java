package com.constantineqaq.dal.manager.impl;

import com.constantineqaq.base.dto.Person;
import com.constantineqaq.dal.dao.PersonDao;
import com.constantineqaq.dal.manager.PersonManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class PersonManagerImpl implements PersonManager {

    @Resource
    private PersonDao personDao;


    @Override
    public List<Person> getAllPerson() {
        return personDao.getAllPerson();
    }

    @Override
    public Person getPersonById(int id) {
        return personDao.getPersonById(id);
    }

    @Override
    public void addPerson(Person person) {
        personDao.addPerson(person);
    }

    @Override
    public void updatePerson(Person person) {
        personDao.updatePerson(person);
    }

    @Override
    public void deletePersonById(int id) {
        personDao.deletePersonById(id);
    }
}
