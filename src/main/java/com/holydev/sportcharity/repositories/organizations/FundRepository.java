package com.holydev.sportcharity.repositories.organizations;

import com.holydev.sportcharity.entities.organizations.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<Fund, Long> {
}
