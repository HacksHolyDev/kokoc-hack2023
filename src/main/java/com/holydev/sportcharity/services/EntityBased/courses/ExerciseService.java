package com.holydev.sportcharity.services.EntityBased.courses;

import com.holydev.sportcharity.repositories.courses.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepo;
}
