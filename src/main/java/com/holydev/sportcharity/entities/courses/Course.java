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

    @Column(nullable = false, unique = true)
    private String description;

    @Column
    private int total_trainings_count;

    @Column
    private int total_cost;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;


    @OneToMany(mappedBy = "course", orphanRemoval = true)
    @ToString.Exclude
    private Set<CourseUserFundMapper> courseUserFundMappers = new LinkedHashSet<>();

}
