package com.holydev.sportcharity.repositories.organizations;

import com.holydev.sportcharity.entities.organizations.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Override
    @Query(value = "select t from Department t where t.id = ?1 and t.deleted <> true")
    Optional<Department> findById(Long aLong);

    @Query(value = "select t from Department t where t.name = ?1 and t.deleted <> true")
    Optional<Department> findByName(String name);

    @Override
    @Query(value = "select t from Department t where t.deleted <> true")
    List<Department> findAll();
}
