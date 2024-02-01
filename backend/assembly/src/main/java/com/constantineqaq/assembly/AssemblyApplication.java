package com.constantineqaq.assembly;

import net.devh.boot.grpc.server.autoconfigure.GrpcServerSecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangyaning3
 */
@SpringBootApplication(exclude = {GrpcServerSecurityAutoConfiguration.class})
@ComponentScan({
        "com.constantineqaq"
})
@MapperScan("com.constantineqaq.dal.mapper")
public class AssemblyApplication {
        public static void main(String[] args) {
                SpringApplication.run(AssemblyApplication.class, args);
        }
}
