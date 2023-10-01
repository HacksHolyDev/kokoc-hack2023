package com.holydev.sportcharity.services.EntityBased.courses;

import com.holydev.sportcharity.DTO.courses.CourseDTO.CourseInfo;
import com.holydev.sportcharity.entities.courses.Course;
import com.holydev.sportcharity.global_utilities.Exceptions.AlreadyExistException;
import com.holydev.sportcharity.global_utilities.Exceptions.NotFoundException;
import com.holydev.sportcharity.repositories.courses.CourseRepository;
import com.holydev.sportcharity.repositories.courses.TrainingRepository;
import com.holydev.sportcharity.repositories.users.UserRepository;
import com.holydev.sportcharity.services.RoleBased.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepo;

    private final TrainingService trainingService;
    private final TrainingRepository trainingRepository;

    private final UserService userService;
    private final UserRepository userRepository;

    public List<Course> getAll() {
        return courseRepo.findAll();
    }

    public Course get(long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found object id = %d", id)));
    }

    public List<Course> getUser(long id) {
        return userService.getUser(id).getCourses()
                .stream().filter(x -> !x.isDeleted())
                .toList();
    }

    public Course create(CourseInfo info) {
        var existObject = courseRepo.findByName(info.getName());
        if (existObject.isPresent()) {
            throw new AlreadyExistException(String.format("Exist object name = %s", info.getName()));
        }

        var object = new Course();
        object.setName(info.getName());
        object.setDescription(info.getDescription());

        return courseRepo.save(object);
    }

    public void update(long id, CourseInfo info) {
        var existObject = courseRepo.findById(id);
        if (existObject.isEmpty()) {
            throw new NotFoundException(String.format("Not found object id = %d", id));
        }

        var object = existObject.get();
        object.setName(info.getName());
        object.setDescription(info.getDescription());

        courseRepo.save(object);
    }

    public void delete(long id) {
        var existObject = courseRepo.findById(id);
        if (existObject.isEmpty()) {
            throw new NotFoundException(String.format("Not found object id = %d", id));
        }

        var object = existObject.get();
        object.setDeleted(true);
        courseRepo.save(object);
    }

    public void attachUser(long id, long courseId) {
        var user = userService.getUser(id);
        var course = get(courseId);

        user.getCourses().add(course);
        userRepository.save(user);

        course.getUsers().add(user);
        courseRepo.save(course);
    }

    public void detachUser(long id, long courseId) {
        var user = userService.getUser(id);
        var course = get(courseId);

        user.getCourses().remove(course);
        userRepository.save(user);

        course.getUsers().remove(user);
        courseRepo.save(course);
    }

    public void addTraining(long id, long courseId) {
        var training = trainingService.get(id);
        var course = get(courseId);

        training.getCourses().add(course);
        trainingRepository.save(training);

        course.setTotal_trainings_count(course.getTotal_trainings_count() + 1);
        course.setTotal_cost(course.getTotal_cost() + training.getTraining_cost());
        course.getTrainings().add(training);
        courseRepo.save(course);
    }

    public void removeTraining(long id, long courseId) {
        var training = trainingService.get(id);
        var course = get(courseId);

        training.getCourses().remove(course);
        trainingRepository.save(training);

        course.setTotal_trainings_count(course.getTotal_trainings_count() - 1);
        course.setTotal_cost(course.getTotal_cost() - training.getTraining_cost());
        course.getTrainings().remove(training);
        courseRepo.save(course);
    }
}
