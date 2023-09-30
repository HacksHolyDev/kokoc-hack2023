package com.holydev.sportcharity.services.EntityBased.courses;

import com.holydev.sportcharity.DTO.courses.TrainingDTO.TrainingInfo;
import com.holydev.sportcharity.entities.courses.Training;
import com.holydev.sportcharity.entities.courses.TrainingType;
import com.holydev.sportcharity.global_utilities.Exceptions.AlreadyExistException;
import com.holydev.sportcharity.global_utilities.Exceptions.NotFoundException;
import com.holydev.sportcharity.repositories.courses.ExerciseRepository;
import com.holydev.sportcharity.repositories.courses.TrainingRepository;
import com.holydev.sportcharity.services.RoleBased.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingService {
    private final TrainingRepository trainingRepo;
    private final ExerciseService exerciseService;

    private final ExerciseRepository exerciseRepo;
    private final UserService userService;

    public List<Training> getAll() {
        return trainingRepo.findAll();
    }

    public Training get(long id) {
        return trainingRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found object id = %d", id)));
    }

    public Training create(TrainingInfo info) {
        var existObject = trainingRepo.findByName(info.getName());
        if (existObject.isPresent()) {
            throw new AlreadyExistException(String.format("Exist object name = %s", info.getName()));
        }

        var object = new Training();
        object.setName(info.getName());
        object.setTraining_type(TrainingType.valueOf(info.getTraining_type()));

        return trainingRepo.save(object);
    }

    public void update(long id, TrainingInfo info) {
        var existObject = trainingRepo.findById(id);
        if (existObject.isEmpty()) {
            throw new NotFoundException(String.format("Not found object id = %d", id));
        }

        var object = existObject.get();
        object.setName(info.getName());
        object.setTraining_type(TrainingType.valueOf(info.getTraining_type()));

        trainingRepo.save(object);
    }

    public void delete(long id) {
        var existObject = trainingRepo.findById(id);
        if (existObject.isEmpty()) {
            throw new NotFoundException(String.format("Not found object id = %d", id));
        }

        var object = existObject.get();
        object.setDeleted(true);
        trainingRepo.save(object);
    }
}
