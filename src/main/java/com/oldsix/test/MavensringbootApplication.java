package com.oldsix.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.oldsix.test.datasource.mapper")
@SpringBootApplication
public class MavensringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MavensringbootApplication.class, args);
    }

}
