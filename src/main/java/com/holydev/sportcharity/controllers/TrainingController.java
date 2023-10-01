package com.holydev.sportcharity.controllers;

import com.holydev.sportcharity.DTO.courses.TrainingDTO.TrainingExerciseInfo;
import com.holydev.sportcharity.DTO.courses.TrainingDTO.TrainingInfo;
import com.holydev.sportcharity.entities.courses.Training;
import com.holydev.sportcharity.services.EntityBased.courses.TrainingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training")
@RequiredArgsConstructor
public class TrainingController {
    private final TrainingService trainingService;

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT", "USER"})
    @Operation(summary = "get all training")
    @GetMapping
    public ResponseEntity<List<TrainingInfo>> getAll() {
        var objects = trainingService.getAll()
                .stream().map(this::convert)
                .toList();
        return ResponseEntity.ok(objects);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT", "USER"})
    @Operation(summary = "get training")
    @GetMapping("/{id}")
    public ResponseEntity<TrainingInfo> get(@PathVariable long id) {
        var object = trainingService.get(id);
        return ResponseEntity.ok(convert(object));
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "Create training")
    @PostMapping
    public ResponseEntity<TrainingInfo> create(@RequestBody TrainingInfo info) {
        var object = trainingService.create(info);
        return ResponseEntity.ok(convert(object));
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "update training")
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody TrainingInfo info) {
        trainingService.update(id, info);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "delete training")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        trainingService.delete(id);
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "add exercise to training")
    @PostMapping("/add")
    public void add(@RequestBody TrainingExerciseInfo info) {
        trainingService.addExercise(info.getExerciseId(), info.getTrainingId());
    }

    @RolesAllowed({"ADMIN", "DEP_HEAD", "FUND_AGENT"})
    @Operation(summary = "remove exercise from training")
    @PostMapping("/remove")
    public void remove(@RequestBody TrainingExerciseInfo info) {
        trainingService.removeExercise(info.getExerciseId(), info.getTrainingId());
    }

    private TrainingInfo convert(Training training) {
        return TrainingInfo.builder()
                .id(training.getId())
                .name(training.getName())
                .training_type(training.getTraining_type().name())
                .training_cost(training.getTraining_cost())
                .build();
    }
}
