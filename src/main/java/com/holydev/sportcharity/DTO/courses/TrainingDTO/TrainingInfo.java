package com.holydev.sportcharity.DTO.courses.TrainingDTO;

import com.holydev.sportcharity.DTO.courses.ExerciseDTO.ExerciseInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class TrainingInfo {
    private Long id;

    private String training_type;

    private int training_cost;

    private List<ExerciseInfo> exercises;
}
