package me.eminem.katalogRSO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class KatalogRsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KatalogRsoApplication.class, args);
		System.out.println("Pozdrav, aplikacija se je zaštartala");
	}

}
