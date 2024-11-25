package ua.edu.chnu.tasks_api.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository repository;

    public Task create(Task task) {
        return repository.save(task);
    }

    public List<Task> readAll() {
        return repository.findAll();
    }

    public Task read(int id) {
        return repository.findById(id).orElse(null);
    }

    public void update(int id, Task task) {
        if (isValid(id)) {
            task.setId(id);
            repository.save(task);
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
