package it.uniroma3.siw.streamNet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class StreamNetApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamNetApplication.class, args);
	}
	


}
