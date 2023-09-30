package com.holydev.sportcharity.services.EntityBased.courses;

import com.holydev.sportcharity.DTO.courses.CourseDTO.CourseInfo;
import com.holydev.sportcharity.entities.courses.Course;
import com.holydev.sportcharity.global_utilities.Exceptions.AlreadyExistException;
import com.holydev.sportcharity.global_utilities.Exceptions.NotFoundException;
import com.holydev.sportcharity.repositories.courses.CourseRepository;
import com.holydev.sportcharity.services.RoleBased.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepo;
    private final ExerciseService exerciseService;
    private final TrainingService trainingService;
    private final UserService userService;

    public List<Course> getCourses() {
        return courseRepo.findAll();
    }

    public Course getCourse(long courseId) {
        return courseRepo.findById(courseId)
                .orElseThrow(() -> new NotFoundException(String.format("Not found course id = %d", courseId)));
    }

    public void createCourse(long userId, CourseInfo info) {
        var user = userService.getUser(userId);

        var existCourse = courseRepo.findByName(info.getName());
        if (existCourse.isPresent()) {
            throw new AlreadyExistException(String.format("Exist course name = %s", info.getName()));
        }

        var course = new Course();
        course.setName(info.getName());
        course.setDescription(info.getDescription());
        course.setTotal_cost(info.getTotalCost());
        course.setTotal_trainings_count(info.getTotalTrainingsCount());
        course.setCreator(user);

        courseRepo.save(course);
    }

    public void updateCourse(long courseId, CourseInfo info) {
        var existCourse = courseRepo.findById(courseId);
        if (existCourse.isEmpty()) {
            throw new NotFoundException(String.format("Not found course id = %d", courseId));
        }

        var course = existCourse.get();
        course.setName(info.getName());
        course.setDescription(info.getDescription());
        course.setTotal_cost(info.getTotalCost());
        course.setTotal_trainings_count(info.getTotalTrainingsCount());

        courseRepo.save(course);
    }

    public void deleteCourse(long courseId) {
        var existCourse = courseRepo.findById(courseId);
        if (existCourse.isEmpty()) {
            throw new NotFoundException(String.format("Not found course id = %d", courseId));
        }

        var course = existCourse.get();
        course.setDeleted(true);
        courseRepo.save(course);
    }
}
