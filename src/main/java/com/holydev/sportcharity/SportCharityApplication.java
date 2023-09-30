package com.holydev.sportcharity;

import com.holydev.sportcharity.entities.users.Role;
import com.holydev.sportcharity.entities.users.User;
import com.holydev.sportcharity.repositories.users.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SportCharityApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SportCharityApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Bean
    ApplicationRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        if (userRepository.findAll().isEmpty()) {
            var admin = new User();
            admin.setEmail("1");
            admin.setFio("1");
            admin.setPassword(passwordEncoder.encode("1"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);

            admin = new User();
            admin.setEmail("2");
            admin.setFio("2");
            admin.setPassword(passwordEncoder.encode("2"));
            admin.setRole(Role.USER);
            userRepository.save(admin);

            admin = new User();
            admin.setEmail("3");
            admin.setFio("3");
            admin.setPassword(passwordEncoder.encode("3"));
            admin.setRole(Role.DEP_HEAD);
            userRepository.save(admin);

            admin = new User();
            admin.setEmail("4");
            admin.setFio("4");
            admin.setPassword(passwordEncoder.encode("4"));
            admin.setRole(Role.FUND_AGENT);
            userRepository.save(admin);
        }

        return args -> {
        };
    }

}
