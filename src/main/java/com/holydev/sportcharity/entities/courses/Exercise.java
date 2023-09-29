package com.holydev.sportcharity.entities.courses;

import com.holydev.sportcharity.entities.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "excercises")
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

    @Column(nullable = false, unique = true)
    private String description;

    @Column
    private String href;


    @Column(nullable = false)
    private int cost_per_retry;

    @Column(nullable = false)
    private int minimal_retry;

    @Column(nullable = false)
    private int maximal_retry;

    @ToString.Exclude
    @OneToMany(orphanRemoval = true)
    @Builder.Default
    private Set<Exercise> similar_exercises = new LinkedHashSet<>();

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Enumerated(EnumType.STRING)
    private TrainingType training_type;


    @ManyToMany(mappedBy = "exercises")
    @ToString.Exclude
    @Builder.Default
    private Set<Training> trainings = new LinkedHashSet<>();

}
