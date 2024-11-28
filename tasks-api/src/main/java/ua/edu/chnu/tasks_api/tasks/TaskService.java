package ua.edu.chnu.tasks_api.tasks;

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

    public List<Task> readAll() {
        return repository.findAll();
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
