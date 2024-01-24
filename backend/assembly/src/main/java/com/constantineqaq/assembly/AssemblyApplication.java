package com.constantineqaq.assembly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangyaning33
 */
@SpringBootApplication
@ComponentScan({
        "com.constantineqaq"
})
@MapperScan({
        "com.constantineqaq.dal.dao"
})
public class AssemblyApplication {
        public static void main(String[] args) {
                SpringApplication.run(AssemblyApplication.class, args);
        }
}
