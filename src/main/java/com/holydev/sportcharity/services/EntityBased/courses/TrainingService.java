package com.holydev.sportcharity.services.EntityBased.courses;

import com.holydev.sportcharity.DTO.courses.ExerciseDTO.ExerciseInfo;
import com.holydev.sportcharity.DTO.courses.TrainingDTO.TrainingInfo;
import com.holydev.sportcharity.entities.courses.Exercise;
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
                .orElseThrow(() -> new NotFoundException(String.format("Not found training id = %d", id)));
    }

    public void create(long userId, TrainingInfo info) {
        var user = userService.getUser(userId);

        var course = new Training();
        course.setTraining_type(TrainingType.valueOf(info.getTraining_type()));

        trainingRepo.save(course);
    }

    public void update(long courseId, ExerciseInfo info) {
        var existCourse = trainingRepo.findById(courseId);
        if (existCourse.isEmpty()) {
            throw new NotFoundException(String.format("Not found exer id = %d", courseId));
        }

        var course = existCourse.get();
        course.setTraining_type(TrainingType.valueOf(info.getTraining_type()));

        trainingRepo.save(course);
    }

    public void delete(long courseId) {
        var existCourse = trainingRepo.findById(courseId);
        if (existCourse.isEmpty()) {
            throw new NotFoundException(String.format("Not found exer id = %d", courseId));
        }

        var course = existCourse.get();
        trainingRepo.delete(course);
    }
}
