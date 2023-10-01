package com.holydev.sportcharity.services.EntityBased.utilities;

import com.holydev.sportcharity.entities.users.Role;
import com.holydev.sportcharity.entities.users.User;
import com.holydev.sportcharity.repositories.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final UserRepository userRepo;

    public List<User> getTop10Money() {
        var tmp = userRepo.findDistinctByRoleOrderByMoneyDesc(Role.USER);
        return tmp.subList(0, Math.min(10, tmp.size()));
    }

}
