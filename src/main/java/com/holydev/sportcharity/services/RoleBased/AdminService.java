package com.holydev.sportcharity.services.RoleBased;

import com.holydev.sportcharity.repositories.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepo;
    private final UserService userService;
}
