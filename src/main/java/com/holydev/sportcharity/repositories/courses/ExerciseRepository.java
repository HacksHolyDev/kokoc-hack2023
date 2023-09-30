package com.holydev.sportcharity.repositories.courses;

import com.holydev.sportcharity.entities.courses.Course;
import com.holydev.sportcharity.entities.courses.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Course> findByName(String name);
}
