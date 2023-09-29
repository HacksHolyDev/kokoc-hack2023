package com.holydev.sportcharity.entities.organizations;

import com.holydev.sportcharity.entities.users.User;
import com.holydev.sportcharity.entities.utilities.CourseUserFundMapper;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "funds")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Fund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(unique = true)
    private String description;

    @ElementCollection
    @CollectionTable(name = "funds_charity_categories", joinColumns = @JoinColumn(name = "owner_id"))
    private List<CharityCategory> charityCategories = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "fund", orphanRemoval = true)
    private Set<User> agents = new LinkedHashSet<>();

    @OneToMany(mappedBy = "fund", orphanRemoval = true)
    @ToString.Exclude
    private Set<CourseUserFundMapper> courseUserFundMappers = new LinkedHashSet<>();

}
