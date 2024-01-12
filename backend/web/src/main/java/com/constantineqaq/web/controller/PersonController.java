package com.constantineqaq.web.controller;

import com.constantineqaq.base.dto.Person;
import com.constantineqaq.web.grpc.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Resource
    private PersonService personService;

    @GetMapping("/getAllPerson")
    public List<Person> getAllPerson() {
        return personService.getAllPerson();
    }
}
