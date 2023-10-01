package com.holydev.sportcharity.entities.users;


import com.holydev.sportcharity.entities.courses.Course;
import com.holydev.sportcharity.entities.organizations.Department;
import com.holydev.sportcharity.entities.organizations.Fund;
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

    @Column(unique = true)
    private String email;

    @Column
    private String fio;

    @Column(nullable = false, unique = true)
    //@Setter(AccessLevel.NONE)
    @ToString.Exclude
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private int money;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Token> tokens;

    @ManyToMany
    @Builder.Default
    @ToString.Exclude
    @JoinTable(name = "users_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new LinkedHashSet<>();


    @ToString.Exclude
    @OneToMany
    private Set<Planner> planners = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "fund_id")
    private Fund fund;

    @OneToOne(mappedBy = "head", orphanRemoval = true)
    private Department head_of_department;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


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
