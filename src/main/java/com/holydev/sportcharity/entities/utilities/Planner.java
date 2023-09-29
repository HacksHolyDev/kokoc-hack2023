package com.holydev.sportcharity.entities.utilities;


import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

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

    @ToString.Exclude
    @OneToMany(mappedBy = "owning_planner", orphanRemoval = true)
    @Builder.Default
    private Set<Timer> train_timers = new LinkedHashSet<>();

}
