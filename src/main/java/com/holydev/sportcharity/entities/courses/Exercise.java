package com.holydev.sportcharity.entities.courses;

import com.holydev.sportcharity.entities.utilities.Timer;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "exercises")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Column
    private String href;

    @Column(nullable = false)
    private int cost_per_retry;

    @Column(nullable = false)
    private int minimal_retry;

    @Column(nullable = false)
    private int maximal_retry;

    @Column
    private boolean deleted;

/*    @ToString.Exclude
    @OneToMany(orphanRemoval = true)
    @Builder.Default
    private Set<Exercise> similar_exercises = new LinkedHashSet<>();*/

    @Enumerated(EnumType.STRING)
    private TrainingType training_type;

    @ManyToMany
    @ToString.Exclude
    @Builder.Default
    @JoinTable(name = "trainings_exercises",
            joinColumns = @JoinColumn(name = "exercises_id"),
            inverseJoinColumns = @JoinColumn(name = "training_id"))
    private Set<Training> trainings = new LinkedHashSet<>();

    @OneToMany
    @ToString.Exclude
    @Builder.Default
    private Set<Timer> timers = new LinkedHashSet<>();

}
