package com.holydev.sportcharity.services.EntityBased.utilities;

import com.holydev.sportcharity.repositories.utilities.PlannerRepository;
import com.holydev.sportcharity.services.EntityBased.courses.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlannerService {
    private final PlannerRepository plannerRepo;
    private final TrainingService trainingService;
    private final TimerService timerService;
}
