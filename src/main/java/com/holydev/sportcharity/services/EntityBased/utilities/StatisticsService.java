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

    public List<trainC> getTop10TrainCount() {
        var tmp = userRepo.findByRole(Role.USER);
        var tcount = tmp.stream().map(x -> new trainC(x.getFio(), x.getPlanner().getTrain_timers().size())).toList();
        return tcount.subList(0, Math.min(10, tcount.size()));
    }

    public List<User> getTop10Money() {
        var tmp = userRepo.findDistinctByRoleOrderByMoneyDesc(Role.USER);
        return tmp.subList(0, Math.min(10, tmp.size()));
    }

    public record trainC(String fio, int amount) {
    }

}
