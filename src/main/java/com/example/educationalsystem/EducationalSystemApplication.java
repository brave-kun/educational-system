package com.example.educationalsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.educationalsystem.mapper")
@SpringBootApplication
public class EducationalSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationalSystemApplication.class, args);
    }

}
