package com.holydev.sportcharity.repositories.courses;

import com.holydev.sportcharity.entities.courses.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    @Override
    @Query(value = "select t from Training t where t.id = ?1 and t.deleted <> true")
    Optional<Training> findById(Long aLong);

    @Query(value = "select t from Training t where t.name = ?1 and t.deleted <> true")
    Optional<Training> findByName(String name);

    @Override
    @Query(value = "select t from Training t where t.deleted <> true")
    List<Training> findAll();
}
