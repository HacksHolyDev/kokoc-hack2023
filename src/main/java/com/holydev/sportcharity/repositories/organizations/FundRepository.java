package com.holydev.sportcharity.repositories.organizations;

import com.holydev.sportcharity.entities.organizations.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FundRepository extends JpaRepository<Fund, Long> {
    @Override
    @Query(value = "select t from Fund t where t.id = ?1 and t.deleted <> true")
    Optional<Fund> findById(Long aLong);

    @Query(value = "select t from Fund t where t.name = ?1 and t.deleted <> true")
    Optional<Fund> findByName(String name);

    @Override
    @Query(value = "select t from Fund t where t.deleted <> true")
    List<Fund> findAll();
}
