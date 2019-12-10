package com.sun.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class CustomerCenterStartApplication {
	
	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(CustomerCenterStartApplication.class, args);
		//SpringContextUtil.setApplicationContext(app);
	}
	
}
