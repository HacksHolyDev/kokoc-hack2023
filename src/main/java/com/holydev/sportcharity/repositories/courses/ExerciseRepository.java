package com.holydev.sportcharity.repositories.courses;

import com.holydev.sportcharity.entities.courses.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Override
    @Query(value = "select t from Exercise t where t.id = ?1 and t.deleted <> true")
    Optional<Exercise> findById(Long aLong);

    @Query(value = "select t from Exercise t where t.name = ?1 and t.deleted <> true")
    Optional<Exercise> findByName(String name);

    @Override
    @Query(value = "select t from Exercise t where t.deleted <> true")
    List<Exercise> findAll();
}
