package com.holydev.sportcharity.services.EntityBased.courses;

import com.holydev.sportcharity.repositories.courses.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepo;
    private final ExerciseService exerciseService;
    private final TrainingService trainingService;
}
