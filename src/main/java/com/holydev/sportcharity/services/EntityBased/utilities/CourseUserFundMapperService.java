package com.holydev.sportcharity.services.EntityBased.utilities;

import com.holydev.sportcharity.repositories.utilities.CourseUserFundMapperRepository;
import com.holydev.sportcharity.services.EntityBased.courses.CourseService;
import com.holydev.sportcharity.services.EntityBased.organizations.FundService;
import com.holydev.sportcharity.services.RoleBased.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseUserFundMapperService {
    private final CourseService courseService;
    private final UserService userService;
    private final FundService fundService;
    private CourseUserFundMapperRepository repo;
}
