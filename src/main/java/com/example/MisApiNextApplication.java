package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableCaching
@EnableAsync
@EnableScheduling
@ComponentScan("com.example.*")
@MapperScan("com.example.db.dao")
public class MisApiNextApplication {

	public static void main(String[] args) {
		SpringApplication.run(MisApiNextApplication.class, args);
	}

}
