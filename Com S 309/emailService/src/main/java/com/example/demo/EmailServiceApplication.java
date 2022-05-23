package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


@SpringBootApplication
public class EmailServiceApplication {
	KeyGenerator keyGen = KeyGenerator.getInstance("DES");
	SecureRandom secRandom = new SecureRandom();
	
	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

}
