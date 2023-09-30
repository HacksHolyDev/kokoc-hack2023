package com.holydev.sportcharity.repositories.courses;

import com.holydev.sportcharity.entities.courses.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Override
    @Query(value = "select c from Course c where c.id = ?1 and c.deleted <> true")
    Optional<Course> findById(Long aLong);

    @Query(value = "select c from Course c where c.name = ?1 and c.deleted <> true")
    Optional<Course> findByName(String name);

    @Override
    @Query(value = "select c from Course c where c.deleted <> true")
    List<Course> findAll();
}
