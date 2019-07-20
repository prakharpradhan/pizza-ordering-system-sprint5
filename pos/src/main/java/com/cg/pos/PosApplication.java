package com.cg.pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.cg.pos"})
@SpringBootApplication
public class PosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosApplication.class, args);
	}

}
