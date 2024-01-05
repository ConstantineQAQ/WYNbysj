package com.constantineqaq.assembly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "com.constantineqaq"
})
public class AssemblyApplication {
        public static void main(String[] args) {
            SpringApplication.run(AssemblyApplication.class, args);
        }
}
