package ua.edu.chnu.courses_api.courses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseService {
    private final CourseRepository repository;

    public Course create(Course course) {
        return repository.save(course);
    }

    public List<Course> readAll() {
        return repository.findAll();
    }

    public Course read(int id) {
        return repository.findById(id).orElse(null);
    }

    public void update(int id, Course course) {
        if (isValid(id)) {
            course.setId(id);
            repository.save(course);
        }
    }

    public void delete(int id) {
        if (isValid(id)) {
            repository.deleteById(id);
        }
    }

    public boolean isValid(int id) {
        return repository.existsById(id);
    }
}
