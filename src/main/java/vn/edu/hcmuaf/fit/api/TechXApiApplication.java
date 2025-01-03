package vn.edu.hcmuaf.fit.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TechXApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechXApiApplication.class, args);
	}

}
