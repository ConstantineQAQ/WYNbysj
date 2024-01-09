package com.example.client_test;

import com.example.client_test.grpc.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClientTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ClientTestApplication.class, args);
        PersonService personService = applicationContext.getBean(PersonService.class);
        personService.getAllPerson();
    }

}
