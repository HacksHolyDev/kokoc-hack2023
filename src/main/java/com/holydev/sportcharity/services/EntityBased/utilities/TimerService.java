package com.holydev.sportcharity.services.EntityBased.utilities;

import com.holydev.sportcharity.repositories.utilities.TimerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimerService {
    private final TimerRepository timerRepo;
}
