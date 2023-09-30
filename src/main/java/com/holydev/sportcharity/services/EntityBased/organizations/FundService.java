package com.holydev.sportcharity.services.EntityBased.organizations;

import com.holydev.sportcharity.DTO.organizations.FundDTO.FundInfo;
import com.holydev.sportcharity.entities.organizations.Fund;
import com.holydev.sportcharity.global_utilities.Exceptions.AlreadyExistException;
import com.holydev.sportcharity.global_utilities.Exceptions.NotFoundException;
import com.holydev.sportcharity.repositories.organizations.FundRepository;
import com.holydev.sportcharity.repositories.users.UserRepository;
import com.holydev.sportcharity.services.RoleBased.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FundService {
    private final FundRepository fundRepository;

    private final UserRepository userRepository;
    private final UserService userService;

    public List<Fund> getAll() {
        return fundRepository.findAll();
    }

    public Fund get(long id) {
        return fundRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found object id = %d", id)));
    }

    public Fund create(FundInfo info) {
        var existObject = fundRepository.findByName(info.getName());
        if (existObject.isPresent()) {
            throw new AlreadyExistException(String.format("Exist object name = %s", info.getName()));
        }

        var object = new Fund();
        object.setName(info.getName());
        object.setDescription(info.getDescription());
        object.setBudget(info.getBudget());

        return fundRepository.save(object);
    }

    public void update(long id, FundInfo info) {
        var existObject = fundRepository.findById(id);
        if (existObject.isEmpty()) {
            throw new NotFoundException(String.format("Not found object id = %d", id));
        }

        var object = existObject.get();
        object.setName(info.getName());
        object.setDescription(info.getDescription());
        object.setBudget(info.getBudget());

        fundRepository.save(object);
    }

    public void delete(long id) {
        var existObject = fundRepository.findById(id);
        if (existObject.isEmpty()) {
            throw new NotFoundException(String.format("Not found object id = %d", id));
        }

        var object = existObject.get();
        object.setDeleted(true);
        fundRepository.save(object);
    }

    public void addUser(long id, long fundId) {
        var user = userService.getUser(id);
        var fund = get(fundId);

        fund.getAgents().add(user);
        fundRepository.save(fund);

        user.setFund(fund);
        userRepository.save(user);
    }

    public void removeUser(long id, long fundId) {
        var user = userService.getUser(id);
        var fund = get(fundId);

        fund.getAgents().remove(user);
        fundRepository.save(fund);

        user.setFund(null);
        userRepository.save(user);
    }
}
