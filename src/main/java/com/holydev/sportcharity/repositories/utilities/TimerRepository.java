package com.holydev.sportcharity.repositories.utilities;

import com.holydev.sportcharity.entities.utilities.Timer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimerRepository extends JpaRepository<Timer, Long> {
    @Query(value = "select t from Timer t where t.user.id = ?1")
    List<Timer> findByUserId(long id);
}
