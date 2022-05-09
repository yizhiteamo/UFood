package com.yizhiteamo.ufood;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yizhiteamo.ufood.mapper")
public class UfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(UfoodApplication.class, args);
	}

}
