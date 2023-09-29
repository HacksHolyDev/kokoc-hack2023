package com.holydev.sportcharity.entities.users;


import com.holydev.sportcharity.entities.courses.Course;
import com.holydev.sportcharity.entities.courses.Exercise;
import com.holydev.sportcharity.entities.utilities.Planner;
import com.holydev.sportcharity.global_utilities.Token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String fio;

    @Column(nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Token> tokens;

    @OneToMany(mappedBy = "creator", orphanRemoval = true)
    @Builder.Default
    private Set<Course> created_courses = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "users_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id"))
    @Builder.Default
    private Set<Course> attended_courses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "creator", orphanRemoval = true)
    @Builder.Default
    private Set<Exercise> created_exercises = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "planner_id")
    private Planner planner;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
