package ua.edu.chnu.tasks_api.tasks;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.edu.chnu.tasks_api.courses.CourseClient;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final CourseClient client;

    public TaskService(TaskRepository repository, CourseClient client) {
        this.repository = repository;
        this.client = client;
    }

    public Task create(Task task) {
        return isExistingCourseId(task) ? repository.save(task) : null;
    }

    public List<Task> readAll(Long courseId, String name, boolean completed, String sortBy, Sort.Direction direction) {
        Sort sort = null;
        if (sortBy != null && !sortBy.isEmpty() && direction != null) {
            sort = Sort.by(direction, sortBy);
        }

        if (sortBy != null && !sortBy.isEmpty()) {
            sort = Sort.by(sortBy);
        }

        if (courseId != null && name != null && !name.isEmpty() && completed && sort != null) {
            return repository.findByCourseIdAndNameContainsIgnoreCaseAndCompleted(courseId, name, true, sort);
        }

        if (courseId != null && name != null && !name.isEmpty() && completed) {
            return repository.findByCourseIdAndNameContainsIgnoreCaseAndCompleted(courseId, name, true);
        }

        if (courseId != null && name != null && !name.isEmpty() && sort != null) {
            return repository.findByCourseIdAndNameContainsIgnoreCase(courseId, name, sort);
        }

        if (courseId != null && name != null && !name.isEmpty()) {
            return repository.findByCourseIdAndNameContainsIgnoreCase(courseId, name);
        }

        if (courseId != null && completed && sort != null) {
            return repository.findByCourseIdAndCompleted(courseId, true, sort);
        }

        if (courseId != null && completed) {
            return repository.findByCourseIdAndCompleted(courseId, true);
        }

        if (name != null && !name.isEmpty() && completed && sort != null) {
            return repository.findByNameContainsIgnoreCaseAndCompleted(name, true, sort);
        }

        if (name != null && !name.isEmpty() && completed) {
            return repository.findByNameContainsIgnoreCaseAndCompleted(name, true);
        }

        if (courseId != null && sort != null) {
            return repository.findByCourseId(courseId, sort);
        }

        if (courseId != null) {
            return repository.findByCourseId(courseId);
        }

        if (name != null && !name.isEmpty() && sort != null) {
            return repository.findByNameContainsIgnoreCase(name, sort);
        }

        if (name != null && !name.isEmpty()) {
            return repository.findByNameContainsIgnoreCase(name);
        }

        if (completed && sort != null) {
            return repository.findByCompleted(true, sort);
        }

        if (completed) {
            return repository.findByCompleted(true);
        }

        return sort == null ? repository.findAll() : repository.findAll(sort);
    }

    public Task read(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(Long id, Task task) {
        if (read(id) == null || !isExistingCourseId(task)) {
            return false;
        }

        task.setId(id);
        repository.save(task);
        return true;
    }

    public boolean delete(Long id) {
        Task task = read(id);
        if (task == null) {
            return false;
        }

        repository.delete(task);
        return true;
    }

    public boolean isExisting(Long id) {
        return repository.existsById(id);
    }

    private boolean isExistingCourseId(Task task) {
        return Boolean.TRUE.equals(client.isExisting(task.getCourseId()).getBody());
    }
}
