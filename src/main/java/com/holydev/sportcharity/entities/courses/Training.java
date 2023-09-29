package com.holydev.sportcharity.entities.courses;


import com.holydev.sportcharity.entities.utilities.Timer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trainings")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TrainingType training_type;

    @Column
    private int training_cost;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "trainings_timers",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "timers_id"))
    @Builder.Default
    private Set<Timer> users_timers = new LinkedHashSet<>();

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "trainings_exercises",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "exercises_id"))
    @Builder.Default
    private List<Exercise> exercises = new ArrayList<>();

}
