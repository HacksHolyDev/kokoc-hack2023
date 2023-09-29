package com.holydev.sportcharity.repositories.courses;

import com.holydev.sportcharity.entities.courses.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
