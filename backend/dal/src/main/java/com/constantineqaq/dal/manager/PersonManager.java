package com.constantineqaq.dal.manager;

import com.constantineqaq.base.pojo.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public interface PersonManager {

    public List<Person> getAllPerson();

    public Person getPersonById(int id);

    public void addPerson(Person person);

    public void updatePerson(Person person);

    public void deletePersonById(int id);
}
