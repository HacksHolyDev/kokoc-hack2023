package com.holydev.sportcharity.DTO.courses.CourseDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseAttachInfo {
    private long courseId;
    private long fundId;
    private List<Integer> days;
}
