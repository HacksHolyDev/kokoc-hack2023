package com.holydev.sportcharity.entities.utilities;

import com.holydev.sportcharity.entities.courses.Training;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedList;
import java.util.List;


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

    // TODO - сделать поля для дат

    private int start_date;

    private int period;

    private int start_time;

    private int duration;


    @ManyToOne
    @JoinColumn(name = "owning_planner_id")
    private Planner owning_planner;


    @ToString.Exclude
    @ManyToMany(mappedBy = "users_timers")
    @Builder.Default
    private List<Training> trainings = new LinkedList<>();

}
