package com.holydev.sportcharity.controllers;


import com.holydev.sportcharity.entities.users.User;
import com.holydev.sportcharity.services.EntityBased.courses.CourseService;
import com.holydev.sportcharity.services.EntityBased.courses.ExerciseService;
import com.holydev.sportcharity.services.EntityBased.courses.TrainingService;
import com.holydev.sportcharity.services.EntityBased.organizations.DepartmentService;
import com.holydev.sportcharity.services.EntityBased.organizations.FundService;
import com.holydev.sportcharity.services.RoleBased.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final DepartmentService departmentService;
    private final FundService fundService;
    private final ExerciseService exerciseService;
    private final CourseService courseService;
    private final TrainingService trainingService;


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    // Взаимодействие с пользователями (User)
    @GetMapping("user/{id}")
    public ResponseEntity<String> getUser(@PathVariable Long id) {
        return null;
    }

    @PostMapping("user")
    public ResponseEntity<String> createUser() {
        return null;
    }

    public ResponseEntity<String> updateUser() {
        return null;
    }

    public ResponseEntity<String> deleteUser() {
        return null;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    // Взаимодействие с отделами (Department)
    @GetMapping("dep/{id}")
    public ResponseEntity<String> getDepartment() {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }

    @PostMapping("dep")
    public ResponseEntity<String> createDepartment() {
        return null;
    }

    public ResponseEntity<String> updateDepartment() {
        return null;
    }

    public ResponseEntity<String> deleteDepartment() {
        return null;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    // Взаимодействие с фондами (Fund)
    @GetMapping("fund/{id}")
    public ResponseEntity<String> getFund() {
        return null;
    }

    @PostMapping("fund")
    public ResponseEntity<String> createFund() {
        return null;
    }

    public ResponseEntity<String> updateFund() {
        return null;
    }

    public ResponseEntity<String> deleteFund() {
        return null;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    // Взаимодействие с упражнениями (Exercise)
    @GetMapping("exc/{id}")
    public ResponseEntity<String> getExercise() {
        return null;
    }

    @PostMapping("exc")
    public ResponseEntity<String> createExercise() {
        return null;
    }

    public ResponseEntity<String> updateExercise() {
        return null;
    }

    public ResponseEntity<String> deleteExercise() {
        return null;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    // Взаимодействие с курсами тренировок (Course)
    @GetMapping("course/{id}")
    public ResponseEntity<String> getCourse() {
        return null;
    }

    @PostMapping("course")
    public ResponseEntity<String> createCourse() {
        return null;
    }

    public ResponseEntity<String> updateCourse() {
        return null;
    }

    public ResponseEntity<String> deleteCourse() {
        return null;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    // Взаимодействие с тренировками (Training)
    @GetMapping("train/{id}")
    public ResponseEntity<String> getTraining(@PathVariable Long id) {
        return null;
    }

    @PostMapping("train")
    public ResponseEntity<String> createTraining() {
        return null;
    }

    public ResponseEntity<String> updateTraining() {
        return null;
    }

    public ResponseEntity<String> deleteTraining() {
        return null;
    }

}
