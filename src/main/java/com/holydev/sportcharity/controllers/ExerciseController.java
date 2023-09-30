package com.holydev.sportcharity.controllers;

import com.holydev.sportcharity.DTO.courses.ExerciseDTO.ExerciseInfo;
import com.holydev.sportcharity.entities.courses.Exercise;
import com.holydev.sportcharity.entities.users.User;
import com.holydev.sportcharity.global_utilities.AuthorityAnnotations.AdminAuth;
import com.holydev.sportcharity.global_utilities.AuthorityAnnotations.UserAuth;
import com.holydev.sportcharity.services.EntityBased.courses.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;

    @UserAuth
    @Operation(summary = "get all exercise")
    @GetMapping
    public ResponseEntity<List<ExerciseInfo>> getAll() {
        var courses = exerciseService.getAll()
                .stream().map(this::convert)
                .toList();

        return ResponseEntity.ok(courses);
    }


    @UserAuth
    @Operation(summary = "get exercise")
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseInfo> getOne(@PathVariable long id) {
        var course = exerciseService.get(id);

        return ResponseEntity.ok(convert(course));
    }

    @AdminAuth
    @Operation(summary = "Create exercise")
    @PostMapping
    public void create(@RequestBody ExerciseInfo info) {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        exerciseService.create(authUser.getId(), info);

    }

    @AdminAuth
    @Operation(summary = "update exercise")
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody ExerciseInfo info) {
        exerciseService.update(id, info);
    }

    @AdminAuth
    @Operation(summary = "delete exercise")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        exerciseService.delete(id);
    }

    private ExerciseInfo convert(Exercise exercise) {
        return ExerciseInfo.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .description(exercise.getDescription())
                .href(exercise.getHref())
                .cost_per_retry(exercise.getCost_per_retry())
                .minimal_retry(exercise.getMinimal_retry())
                .maximal_retry(exercise.getMaximal_retry())
                .training_type(exercise.getTraining_type().name())
                .build();
    }
}
