package com.holydev.sportcharity.repositories.courses;

import com.holydev.sportcharity.entities.courses.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
