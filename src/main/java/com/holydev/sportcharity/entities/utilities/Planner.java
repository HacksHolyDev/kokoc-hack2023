package com.holydev.sportcharity.entities.utilities;


import com.holydev.sportcharity.entities.courses.Training;
import com.holydev.sportcharity.entities.users.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "planners")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Planner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean finished;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;
}
