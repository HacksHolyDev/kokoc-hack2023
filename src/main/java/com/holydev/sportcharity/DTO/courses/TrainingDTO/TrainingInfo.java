package com.holydev.sportcharity.DTO.courses.TrainingDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TrainingInfo {
    private Long id;

    private String name;

    private String training_type;

    private int training_cost;
}
