package dev.protobot.blogcustom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class BlogcustomApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogcustomApplication.class, args);
	}

}
