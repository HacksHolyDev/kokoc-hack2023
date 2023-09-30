package com.holydev.sportcharity.controllers;

import com.holydev.sportcharity.DTO.courses.ExerciseDTO.ExerciseInfo;
import com.holydev.sportcharity.entities.courses.Exercise;
import com.holydev.sportcharity.services.EntityBased.courses.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT", "USER"})
    @Operation(summary = "get all exercise")
    @GetMapping
    public ResponseEntity<List<ExerciseInfo>> getAll() {
        var objects = exerciseService.getAll()
                .stream().map(this::convert)
                .toList();
        return ResponseEntity.ok(objects);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT", "USER"})
    @Operation(summary = "get exercise")
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseInfo> get(@PathVariable long id) {
        var object = exerciseService.get(id);
        return ResponseEntity.ok(convert(object));
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "Create exercise")
    @PostMapping
    public ResponseEntity<ExerciseInfo> create(@RequestBody ExerciseInfo info) {
        var object = exerciseService.create(info);
        return ResponseEntity.ok(convert(object));
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "update exercise")
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody ExerciseInfo info) {
        exerciseService.update(id, info);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
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
