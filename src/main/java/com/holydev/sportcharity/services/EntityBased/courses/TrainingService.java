package com.holydev.sportcharity.services.EntityBased.courses;

import com.holydev.sportcharity.repositories.courses.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainingService {
    private final TrainingRepository trainingRepo;
    private final ExerciseService exerciseService;
}
