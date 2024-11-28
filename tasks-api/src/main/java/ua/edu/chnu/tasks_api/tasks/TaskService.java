package ua.edu.chnu.tasks_api.tasks;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(Task task) {
        return repository.save(task);
    }

    public List<Task> readAll() {
        return repository.findAll();
    }

    public Task read(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(Long id, Task task) {
        if (read(id) == null) {
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
}
