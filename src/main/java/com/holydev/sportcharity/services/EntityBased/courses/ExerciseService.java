package com.holydev.sportcharity.services.EntityBased.courses;

import com.holydev.sportcharity.DTO.courses.ExerciseDTO.ExerciseInfo;
import com.holydev.sportcharity.entities.courses.Exercise;
import com.holydev.sportcharity.entities.courses.TrainingType;
import com.holydev.sportcharity.global_utilities.Exceptions.AlreadyExistException;
import com.holydev.sportcharity.global_utilities.Exceptions.NotFoundException;
import com.holydev.sportcharity.repositories.courses.ExerciseRepository;
import com.holydev.sportcharity.services.RoleBased.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepo;
    private final UserService userService;

    public List<Exercise> getAll() {
        return exerciseRepo.findAll();
    }

    public Exercise get(long id) {
        return exerciseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found exer id = %d", id)));
    }

    public void create(long userId, ExerciseInfo info) {
        var user = userService.getUser(userId);

        var existCourse = exerciseRepo.findByName(info.getName());
        if (existCourse.isPresent()) {
            throw new AlreadyExistException(String.format("Exist exer name = %s", info.getName()));
        }

        var course = new Exercise();
        course.setName(info.getName());
        course.setDescription(info.getDescription());
        course.setHref(info.getHref());
        course.setCost_per_retry(info.getCost_per_retry());
        course.setMinimal_retry(info.getMinimal_retry());
        course.setMaximal_retry(info.getMaximal_retry());
        course.setTraining_type(TrainingType.valueOf(info.getTraining_type()));

        exerciseRepo.save(course);
    }

    public void update(long courseId, ExerciseInfo info) {
        var existCourse = exerciseRepo.findById(courseId);
        if (existCourse.isEmpty()) {
            throw new NotFoundException(String.format("Not found exer id = %d", courseId));
        }

        var course = existCourse.get();
        course.setName(info.getName());
        course.setDescription(info.getDescription());
        course.setHref(info.getHref());
        course.setCost_per_retry(info.getCost_per_retry());
        course.setMinimal_retry(info.getMinimal_retry());
        course.setMaximal_retry(info.getMaximal_retry());
        course.setTraining_type(TrainingType.valueOf(info.getTraining_type()));

        exerciseRepo.save(course);
    }

    public void delete(long courseId) {
        var existCourse = exerciseRepo.findById(courseId);
        if (existCourse.isEmpty()) {
            throw new NotFoundException(String.format("Not found exer id = %d", courseId));
        }

        var course = existCourse.get();
        exerciseRepo.delete(course);
    }
}
