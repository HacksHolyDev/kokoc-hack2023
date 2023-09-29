package com.holydev.sportcharity.entities.utilities;

import com.holydev.sportcharity.entities.courses.Course;
import com.holydev.sportcharity.entities.organizations.Fund;
import com.holydev.sportcharity.entities.users.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseUserFundMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "fund_id")
    private Fund fund;

}
