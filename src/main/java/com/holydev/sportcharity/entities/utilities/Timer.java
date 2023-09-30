package com.holydev.sportcharity.entities.utilities;

import com.holydev.sportcharity.entities.courses.Training;
import com.holydev.sportcharity.entities.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
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

    private String name;

    private String description;

    private LocalDate date;

    private long durationInMinutes;

    private LocalTime start_time;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "owning_planner_id")
    private Planner owning_planner;


    @ToString.Exclude
    @ManyToMany(mappedBy = "users_timers")
    @Builder.Default
    private List<Training> trainings = new LinkedList<>();

}
