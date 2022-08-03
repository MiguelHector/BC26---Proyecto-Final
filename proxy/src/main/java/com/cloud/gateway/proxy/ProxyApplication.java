package com.cloud.gateway.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
public class ProxyApplication {

	@SuppressWarnings("checkstyle:MissingJavadocMethod")
	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
