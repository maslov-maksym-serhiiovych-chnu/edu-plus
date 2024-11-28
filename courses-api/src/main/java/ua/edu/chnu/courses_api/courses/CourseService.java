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

    public List<Course> readAll(String searchTerm, Sort.Direction sortDirection) {
        if (searchTerm != null && !searchTerm.isEmpty() && sortDirection != null) {
            return repository.findByNameContainingIgnoreCase(searchTerm, Sort.by(sortDirection, "name"));
        }

        if (searchTerm != null && !searchTerm.isEmpty()) {
            return repository.findByNameContainingIgnoreCase(searchTerm);
        }

        if (sortDirection != null) {
            return repository.findAll(Sort.by(sortDirection, "name"));
        }

        return repository.findAll();
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
