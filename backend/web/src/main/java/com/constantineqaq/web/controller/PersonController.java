package com.constantineqaq.web.controller;

import com.constantineqaq.base.enums.CommonEnum;
import com.constantineqaq.base.response.HttpResponse;
import com.constantineqaq.base.entity.Person;
import com.constantineqaq.service.PersonService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author wangyaning33
 */
@RestController
@RequestMapping("/person")
@CrossOrigin(origins = {"http://localhost:5173","http://localhost:58449"}, maxAge = 3600)
public class PersonController {

    @Resource
    private PersonService personService;

    @GetMapping("/getAllPerson")
    @PreAuthorize("hasAnyAuthority('test')")
    public HttpResponse<List<Person>> getAllPerson() {
        return HttpResponse.success(personService.getAllPerson());
    }

    @GetMapping("/getPersonById")
    public HttpResponse<Person> getPersonById(@RequestParam Integer id) {
        return HttpResponse.success(personService.getPersonById(id));
    }

    @PostMapping("/addPerson")
    public HttpResponse<String> addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return HttpResponse.success(CommonEnum.SUCCESS.getMessage());
    }
}
