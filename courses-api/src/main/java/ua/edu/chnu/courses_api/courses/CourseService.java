package ua.edu.chnu.courses_api.courses;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Course create(Course course) {
        return repository.save(course);
    }

    public List<Course> readAll(String name, Sort.Direction direction) {
        Sort sort = direction == null ? Sort.unsorted() : Sort.by(direction, "name");
        return name == null || name.isEmpty() ? repository.findAll(sort) :
                repository.findByNameContainsIgnoreCase(name, sort);
    }

    public Course read(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(Long id, Course course) {
        if (read(id) == null) {
            return false;
        }

        course.setId(id);
        repository.save(course);
        return true;
    }

    public boolean delete(Long id) {
        Course course = read(id);
        if (course == null) {
            return false;
        }

        repository.delete(course);
        return true;
    }

    public boolean isExisting(Long id) {
        return repository.existsById(id);
    }
}
