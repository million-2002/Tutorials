package com.demo.newproject;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.demo.newporject.mapper")
public class NewprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewprojectApplication.class, args);
	}

}
