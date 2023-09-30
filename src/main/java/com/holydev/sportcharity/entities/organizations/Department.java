package com.holydev.sportcharity.entities.organizations;


import com.holydev.sportcharity.entities.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "funds")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;
    
    @Column
    private boolean deleted;

    @ToString.Exclude
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "head_id")
    private User head;

    @ToString.Exclude
    @OneToMany(mappedBy = "department", orphanRemoval = true)
    private Set<User> users = new LinkedHashSet<>();

}
