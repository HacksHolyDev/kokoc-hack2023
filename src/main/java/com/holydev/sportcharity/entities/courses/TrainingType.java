package com.holydev.sportcharity.entities.courses;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TrainingType {

    STRENGTH("На силу"),

    CARDIO("На кардио"),

    MSS("На опорно-двигательную систему"),

    COORDINATION("На координацию");

    private final String title;
}
