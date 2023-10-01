package com.holydev.sportcharity.entities.utilities;

import com.holydev.sportcharity.entities.courses.Exercise;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "timers")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Timer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private LocalDate date;

    private long durationInMinutes;

    private LocalTime start_time;

    @ManyToOne
    @JoinColumn(name = "owning_planner_id")
    private Planner owning_planner;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

}
