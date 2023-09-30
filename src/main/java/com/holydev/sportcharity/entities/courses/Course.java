package com.holydev.sportcharity.entities.courses;


import com.holydev.sportcharity.entities.users.User;
import com.holydev.sportcharity.entities.utilities.CourseUserFundMapper;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Column
    private int total_trainings_count;

    @Column
    private int total_cost;

    @Column
    private boolean deleted;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "users_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new LinkedHashSet<>();

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "course_training",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "training_id"))
    private Set<Training> trainings = new LinkedHashSet<>();


    @OneToMany(mappedBy = "course", orphanRemoval = true)
    @ToString.Exclude
    private Set<CourseUserFundMapper> courseUserFundMappers = new LinkedHashSet<>();

}
