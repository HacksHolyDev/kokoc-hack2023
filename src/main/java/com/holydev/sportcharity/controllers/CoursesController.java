package com.holydev.sportcharity.controllers;

import com.holydev.sportcharity.DTO.courses.CourseDTO.CourseInfo;
import com.holydev.sportcharity.entities.courses.Course;
import com.holydev.sportcharity.entities.users.User;
import com.holydev.sportcharity.global_utilities.AuthorityAnnotations.AdminAuth;
import com.holydev.sportcharity.global_utilities.AuthorityAnnotations.UserAuth;
import com.holydev.sportcharity.services.EntityBased.courses.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CoursesController {
    private final CourseService courseService;

    @UserAuth
    @AdminAuth
    @Operation(summary = "get all courses")
    @GetMapping
    public ResponseEntity<List<CourseInfo>> getCourses() {
        var courses = courseService.getCourses()
                .stream().map(this::convert)
                .toList();

        return ResponseEntity.ok(courses);
    }


    @UserAuth
    @AdminAuth
    @Operation(summary = "get course")
    @GetMapping("/{id}")
    public ResponseEntity<CourseInfo> getCourse(@PathVariable long id) {
        var course = courseService.getCourse(id);

        return ResponseEntity.ok(convert(course));
    }

    @AdminAuth
    @Operation(summary = "Create course")
    @PostMapping
    public void createCourse(@RequestBody CourseInfo info) {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        courseService.createCourse(authUser.getId(), info);

    }

    @AdminAuth
    @Operation(summary = "update course")
    @PutMapping("/{id}")
    public void updateCourse(@PathVariable long id, @RequestBody CourseInfo info) {
        courseService.updateCourse(id, info);
    }

    @AdminAuth
    @Operation(summary = "delete course")
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable long id) {
        courseService.deleteCourse(id);
    }

    private CourseInfo convert(Course course) {
        return CourseInfo.builder()
                .name(course.getName())
                .description(course.getDescription())
                .totalTrainingsCount(course.getTotal_trainings_count())
                .totalCost(course.getTotal_cost())
                .build();
    }
}
