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

    public List<Task> readAll(Long courseId, String name, Boolean completed, SortBy sortBy, Sort.Direction direction) {
        Sort sort = sortBy == null || direction == null ? Sort.unsorted() : Sort.by(direction, sortBy.getProperty());

        if (courseId != null && name != null && !name.isEmpty() && completed != null) {
            return repository.findByCourseIdAndNameContainsIgnoreCaseAndCompleted(courseId, name, completed, sort);
        }

        if (courseId != null && name != null && !name.isEmpty()) {
            return repository.findByCourseIdAndNameContainsIgnoreCase(courseId, name, sort);
        }

        if (courseId != null && completed != null) {
            return repository.findByCourseIdAndCompleted(courseId, completed, sort);
        }

        if (name != null && !name.isEmpty() && completed != null) {
            return repository.findByNameContainsIgnoreCaseAndCompleted(name, completed, sort);
        }

        if (courseId != null) {
            return repository.findByCourseId(courseId, sort);
        }

        if (name != null && !name.isEmpty()) {
            return repository.findByNameContainsIgnoreCase(name, sort);
        }

        return completed == null ? repository.findAll(sort) : repository.findByCompleted(completed, sort);
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

    public boolean setCompleted(Long id) {
        Task task = read(id);
        if (task == null) {
            return false;
        }

        task.setCompleted(true);
        repository.save(task);
        return true;
    }

    public boolean setGrade(Long id, Integer grade) {
        Task task = read(id);
        if (task == null) {
            return false;
        }

        task.setGrade(grade);
        repository.save(task);
        return true;
    }

    public boolean isExisting(Long id) {
        return repository.existsById(id);
    }

    private boolean isExistingCourseId(Task task) {
        return Boolean.TRUE.equals(client.isExisting(task.getCourseId()).getBody());
    }
}
