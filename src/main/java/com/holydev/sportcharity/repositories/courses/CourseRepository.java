package com.holydev.sportcharity.repositories.courses;

import com.holydev.sportcharity.entities.courses.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
