package com.holydev.sportcharity.services.EntityBased.courses;

import com.holydev.sportcharity.DTO.courses.ExerciseDTO.ExerciseInfo;
import com.holydev.sportcharity.entities.courses.Exercise;
import com.holydev.sportcharity.entities.courses.TrainingType;
import com.holydev.sportcharity.global_utilities.Exceptions.AlreadyExistException;
import com.holydev.sportcharity.global_utilities.Exceptions.NotFoundException;
import com.holydev.sportcharity.repositories.courses.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepo;


    public List<Exercise> getAll() {
        return exerciseRepo.findAll();
    }

    public Exercise get(long id) {
        return exerciseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found object id = %d", id)));
    }

    public Exercise create(ExerciseInfo info) {
        var existObject = exerciseRepo.findByName(info.getName());
        if (existObject.isPresent()) {
            throw new AlreadyExistException(String.format("Exist object name = %s", info.getName()));
        }

        var object = new Exercise();
        object.setName(info.getName());
        object.setDescription(info.getDescription());
        object.setHref(info.getHref());
        object.setCost_per_retry(info.getCost_per_retry());
        object.setMinimal_retry(info.getMinimal_retry());
        object.setMaximal_retry(info.getMaximal_retry());
        object.setTraining_type(TrainingType.valueOf(info.getTraining_type()));

        return exerciseRepo.save(object);
    }

    public void update(long id, ExerciseInfo info) {
        var existObject = exerciseRepo.findById(id);
        if (existObject.isEmpty()) {
            throw new NotFoundException(String.format("Not found object id = %d", id));
        }

        var object = existObject.get();
        object.setName(info.getName());
        object.setDescription(info.getDescription());
        object.setHref(info.getHref());
        object.setCost_per_retry(info.getCost_per_retry());
        object.setMinimal_retry(info.getMinimal_retry());
        object.setMaximal_retry(info.getMaximal_retry());
        object.setTraining_type(TrainingType.valueOf(info.getTraining_type()));

        exerciseRepo.save(object);
    }

    public void delete(long id) {
        var existObject = exerciseRepo.findById(id);
        if (existObject.isEmpty()) {
            throw new NotFoundException(String.format("Not found object id = %d", id));
        }

        var object = existObject.get();
        object.setDeleted(true);
        exerciseRepo.save(object);
    }
}
