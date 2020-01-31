package com.formacionbdi.springboot.app.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class SpringbootServicioOauthApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passEncoder;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioOauthApplication.class, args);
	}
	
	

	@Override
	public void run(String... args) throws Exception {
		
		String pass="12345";
		
		System.out.println(passEncoder.encode(pass));
		System.out.println(passEncoder.encode(pass));
		System.out.println(passEncoder.encode(pass));
		System.out.println(passEncoder.encode(pass));
	}

}
