package com.holydev.sportcharity.services.EntityBased.organizations;

import com.holydev.sportcharity.DTO.organizations.DepartmentDTO.DepartmentInfo;
import com.holydev.sportcharity.entities.organizations.Department;
import com.holydev.sportcharity.global_utilities.Exceptions.AlreadyExistException;
import com.holydev.sportcharity.global_utilities.Exceptions.NotFoundException;
import com.holydev.sportcharity.repositories.organizations.DepartmentRepository;
import com.holydev.sportcharity.repositories.users.UserRepository;
import com.holydev.sportcharity.services.RoleBased.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepo;

    private final UserService userService;
    private final UserRepository userRepository;

    public List<Department> getAll() {
        return departmentRepo.findAll();
    }

    public Department get(long id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found object id = %d", id)));
    }

    public Department create(long id, DepartmentInfo info) {
        var user = userService.getUser(id);

        var existObject = departmentRepo.findByName(info.getName());
        if (existObject.isPresent()) {
            throw new AlreadyExistException(String.format("Exist object name = %s", info.getName()));
        }

        var object = new Department();
        object.setName(info.getName());
        object.setDescription(info.getDescription());
        object.setHead(user);

        return departmentRepo.save(object);
    }

    public void update(long id, DepartmentInfo info) {
        var existObject = departmentRepo.findById(id);
        if (existObject.isEmpty()) {
            throw new NotFoundException(String.format("Not found object id = %d", id));
        }

        var object = existObject.get();
        object.setName(info.getName());
        object.setDescription(info.getDescription());

        departmentRepo.save(object);
    }

    public void delete(long id) {
        var existObject = departmentRepo.findById(id);
        if (existObject.isEmpty()) {
            throw new NotFoundException(String.format("Not found object id = %d", id));
        }

        var object = existObject.get();
        object.setDeleted(true);
        departmentRepo.save(object);
    }

    public void addUser(long id, long departmentId) {
        var user = userService.getUser(id);
        var department = get(departmentId);

        department.getUsers().add(user);
        departmentRepo.save(department);

        user.setDepartment(department);
        userRepository.save(user);
    }

    public void removeUser(long id, long departmentId) {
        var user = userService.getUser(id);
        var department = get(departmentId);

        department.getUsers().remove(user);
        departmentRepo.save(department);

        user.setDepartment(null);
        userRepository.save(user);
    }
}
