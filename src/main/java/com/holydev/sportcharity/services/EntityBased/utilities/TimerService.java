package com.holydev.sportcharity.services.EntityBased.utilities;

import com.holydev.sportcharity.entities.utilities.Timer;
import com.holydev.sportcharity.repositories.utilities.TimerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimerService {
    private final TimerRepository timerRepo;

    public List<Timer> getUserTimers(long userId) {
        return List.of();
    }
}
