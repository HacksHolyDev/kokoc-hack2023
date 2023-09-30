package com.holydev.sportcharity.controllers;

import com.holydev.sportcharity.DTO.courses.CourseDTO.CourseInfo;
import com.holydev.sportcharity.entities.courses.Course;
import com.holydev.sportcharity.entities.users.User;
import com.holydev.sportcharity.services.EntityBased.courses.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.RolesAllowed;
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

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT", "USER"})
    @Operation(summary = "get all courses")
    @GetMapping
    public ResponseEntity<List<CourseInfo>> getAll() {
        var objects = courseService.getAll()
                .stream().map(this::convert)
                .toList();
        return ResponseEntity.ok(objects);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT", "USER"})
    @Operation(summary = "get course")
    @GetMapping("/{id}")
    public ResponseEntity<CourseInfo> get(@PathVariable long id) {
        var object = courseService.get(id);
        return ResponseEntity.ok(convert(object));
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "Create course")
    @PostMapping
    public ResponseEntity<CourseInfo> create(@RequestBody CourseInfo info) {
        var object = courseService.create(info);
        return ResponseEntity.ok(convert(object));
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "update course")
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody CourseInfo info) {
        courseService.update(id, info);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "delete course")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        courseService.delete(id);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT", "USER"})
    @Operation(summary = "attach user to course")
    @PostMapping("/attach/{id}")
    public void attach(@PathVariable long id) {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        courseService.attachUser(authUser.getId(), id);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT", "USER"})
    @Operation(summary = "detach user to course")
    @PostMapping("/detach/{id}")
    public void detach(@PathVariable long id) {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        courseService.detachUser(authUser.getId(), id);
    }

    private CourseInfo convert(Course course) {
        return CourseInfo.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .totalTrainingsCount(course.getTotal_trainings_count())
                .totalCost(course.getTotal_cost())
                .build();
    }
}
