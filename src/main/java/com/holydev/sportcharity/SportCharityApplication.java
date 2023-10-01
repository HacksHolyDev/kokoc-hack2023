package com.holydev.sportcharity;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.holydev.sportcharity.entities.courses.Exercise;
import com.holydev.sportcharity.entities.courses.Training;
import com.holydev.sportcharity.entities.courses.TrainingType;
import com.holydev.sportcharity.repositories.courses.CourseRepository;
import com.holydev.sportcharity.repositories.courses.ExerciseRepository;
import com.holydev.sportcharity.repositories.courses.TrainingRepository;
import com.holydev.sportcharity.repositories.organizations.FundRepository;
import com.holydev.sportcharity.repositories.users.UserRepository;
import com.holydev.sportcharity.services.EntityBased.courses.CourseService;
import com.holydev.sportcharity.services.EntityBased.courses.ExerciseService;
import com.holydev.sportcharity.services.EntityBased.courses.TrainingService;
import com.holydev.sportcharity.services.EntityBased.organizations.FundService;
import com.holydev.sportcharity.services.RoleBased.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;

@SpringBootApplication
public class SportCharityApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SportCharityApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }


    @Bean
    public ApplicationRunner init(UserRepository userRepository, UserService userService,
                                  CourseRepository courseRepository, CourseService courseService,
                                  TrainingRepository trainingRepository, TrainingService trainingService,
                                  ExerciseRepository exerciseRepository, ExerciseService exerciseService,
                                  FundRepository fundRepository, FundService fundService,
                                  PasswordEncoder passwordEncoder) {
        var adminSeed = "seed.json";
        if (userRepository.findAll().isEmpty()) {
            if (Files.exists(Path.of(adminSeed))) {
                var parser = new ObjectMapper();
                parser.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                try (Reader reader = new FileReader(adminSeed, StandardCharsets.UTF_8)) {
                    LinkedHashMap<String, List<LinkedHashMap<String, Object>>> seedData = parser.readValue(reader, LinkedHashMap.class);

                    if (userRepository.findAll().isEmpty()) {
                        for (var i : seedData.get("exercises")) {
                            var exercise = new Exercise();
                            exercise.setId(Long.valueOf(i.get("id").toString()));
                            exercise.setName(i.get("name").toString());
                            exercise.setDescription(i.get("description").toString());
                            exercise.setHref(i.get("href").toString());
                            exercise.setCost_per_retry(Integer.parseInt(i.get("cost_per_retry").toString()));
                            exercise.setMinimal_retry(Integer.parseInt(i.get("minimal_retry").toString()));
                            exercise.setMaximal_retry(Integer.parseInt(i.get("maximal_retry").toString()));
                            exercise.setTraining_type(TrainingType.valueOf(i.get("training_type").toString()));
                            exerciseRepository.save(exercise);
                        }

                        for (var i : seedData.get("trainings")) {
                            var exercise = new Training();
                            exercise.setId(Long.valueOf(i.get("id").toString()));
                            exercise.setName(i.get("name").toString());
                            exercise.setTraining_type(TrainingType.valueOf(i.get("training_type").toString()));
                            trainingRepository.save(exercise);
                            //for (var j : (List<Integer>) i.get("exercises")) {
                            //    trainingService.addExercise(j, exercise.getId());
                            //}

                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return args -> System.out.println("Seeding error! IOException!");
                }
                System.out.println("Seeding ended.");
            } else {
                System.out.println("adminseed not exist");
            }
        }
        return args -> {
        };
    }
}
