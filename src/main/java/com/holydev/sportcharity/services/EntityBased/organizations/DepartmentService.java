package com.holydev.sportcharity.services.EntityBased.organizations;

import com.holydev.sportcharity.repositories.organizations.DepartmentRepository;
import com.holydev.sportcharity.repositories.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepo;
    private final UserRepository userRepo;
}
