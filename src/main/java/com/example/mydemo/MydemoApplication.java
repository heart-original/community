package com.example.mydemo;

import com.example.mydemo.mapper.QuestionMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.mydemo.mapper")
@EnableScheduling
public class MydemoApplication {

    @Autowired
    private QuestionMapper questionMapper;

    public static void main(String[] args) {
        SpringApplication.run(MydemoApplication.class, args);
    }

}
