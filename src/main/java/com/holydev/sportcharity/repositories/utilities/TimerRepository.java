package com.holydev.sportcharity.repositories.utilities;

import com.holydev.sportcharity.entities.utilities.Timer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimerRepository extends JpaRepository<Timer, Long> {
}
