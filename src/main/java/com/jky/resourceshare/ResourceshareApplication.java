package com.jky.resourceshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@ComponentScan(basePackages = {"com.jky.resourceshare.*"})
@MapperScan(value = {"com.jky.resourceshare.dao.*"})
@SpringBootApplication
public class ResourceshareApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceshareApplication.class, args);
	}

}
