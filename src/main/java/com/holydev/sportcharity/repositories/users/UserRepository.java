package com.holydev.sportcharity.repositories.users;

import com.holydev.sportcharity.entities.users.Role;
import com.holydev.sportcharity.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findDistinctByRoleOrderByMoneyDesc(Role role);

}
