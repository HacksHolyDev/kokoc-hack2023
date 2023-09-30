package com.holydev.sportcharity.DTO.courses.ExerciseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ExerciseInfo {
    private Long id;

    private String name;

    private String description;

    private String href;

    private int cost_per_retry;

    private int minimal_retry;

    private int maximal_retry;

    private String training_type;
}
