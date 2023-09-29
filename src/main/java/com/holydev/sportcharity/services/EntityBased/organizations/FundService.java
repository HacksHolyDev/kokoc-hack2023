package com.holydev.sportcharity.services.EntityBased.organizations;

import com.holydev.sportcharity.repositories.organizations.FundRepository;
import com.holydev.sportcharity.repositories.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FundService {
    private final FundRepository fundRepo;
    private final UserRepository userRepository;
}
