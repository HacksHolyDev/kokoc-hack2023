package com.holydev.sportcharity.DTO.courses.CourseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CourseInfo {
    private long id;

    private String name;

    private String description;

    private int totalTrainingsCount;

    private int totalCost;
}
