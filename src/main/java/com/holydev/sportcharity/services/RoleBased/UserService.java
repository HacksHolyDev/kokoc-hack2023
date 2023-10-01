package com.holydev.sportcharity.services.RoleBased;

import com.holydev.sportcharity.entities.users.User;
import com.holydev.sportcharity.global_utilities.Exceptions.NotFoundException;
import com.holydev.sportcharity.repositories.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public User getUser(long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found user id = %d", id)));
    }
}
