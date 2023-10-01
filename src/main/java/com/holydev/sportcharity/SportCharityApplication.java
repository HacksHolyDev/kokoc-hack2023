package com.holydev.sportcharity;

import com.holydev.sportcharity.entities.courses.Exercise;
import com.holydev.sportcharity.entities.courses.TrainingType;
import com.holydev.sportcharity.entities.users.Role;
import com.holydev.sportcharity.entities.users.User;
import com.holydev.sportcharity.repositories.courses.ExerciseRepository;
import com.holydev.sportcharity.repositories.courses.TrainingRepository;
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
    ApplicationRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           ExerciseRepository exerciseRepository, TrainingRepository trainingRepository) {
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

        if (exerciseRepository.findAll().isEmpty()) {
            var exercise = new Exercise();
            ;
            exercise.setName("Тестовая");
            exercise.setDescription("ТестовОЕ УПРАЖНЕНИЕ ");
            exercise.setHref("Тест");
            exercise.setCost_per_retry(11);
            exercise.setMinimal_retry(5);
            exercise.setMaximal_retry(12);
            exercise.setTraining_type(TrainingType.CARDIO);
            exerciseRepository.save(exercise);

            exercise = new Exercise();
            ;
            exercise.setName("Ты еблан???");
            exercise.setDescription("Ебобо чтоли ?");
            exercise.setHref("Тест");
            exercise.setCost_per_retry(11222);
            exercise.setMinimal_retry(345);
            exercise.setMaximal_retry(12454);
            exercise.setTraining_type(TrainingType.COORDINATION);
            exerciseRepository.save(exercise);

            exercise = new Exercise();
            ;
            exercise.setName("Откуда у меня эти шрамы");
            exercise.setDescription("Как провести описание");
            exercise.setHref("Тест");
            exercise.setCost_per_retry(99);
            exercise.setMinimal_retry(51);
            exercise.setMaximal_retry(55);
            exercise.setTraining_type(TrainingType.CARDIO);
            exerciseRepository.save(exercise);
        }

        return args -> {
        };
    }

}
