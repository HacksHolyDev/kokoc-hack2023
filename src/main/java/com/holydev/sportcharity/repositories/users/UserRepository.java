package com.holydev.sportcharity.repositories.users;

import com.holydev.sportcharity.entities.users.Role;
import com.holydev.sportcharity.entities.users.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findDistinctByRoleOrderByMoneyDesc(Role role);

    @EntityGraph(attributePaths = {"planner.train_timers"})
    @Query("select u from User u where u.role = ?1")
    List<User> findByRole(Role role);


}
