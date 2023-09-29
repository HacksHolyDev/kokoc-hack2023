package com.holydev.sportcharity.repositories.organizations;

import com.holydev.sportcharity.entities.organizations.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
