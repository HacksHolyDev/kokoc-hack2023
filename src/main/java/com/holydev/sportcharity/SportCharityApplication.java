package com.holydev.sportcharity;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SportCharityApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SportCharityApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

}
