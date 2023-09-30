package com.holydev.sportcharity.entities.courses;


import com.holydev.sportcharity.entities.utilities.Planner;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
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

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private TrainingType training_type;

    @Column
    private int training_cost;

    @ToString.Exclude
    @OneToMany
    @Builder.Default
    private Set<Planner> planners = new LinkedHashSet<>();

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "trainings_exercises",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "exercises_id"))
    @Builder.Default
    private Set<Exercise> exercises = new LinkedHashSet<>();


    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "course_training",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new LinkedHashSet<>();
}
