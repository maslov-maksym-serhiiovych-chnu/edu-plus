package ua.edu.chnu.tasks_api.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/tasks")
public class TaskController {
    private final TaskService service;

    @GetMapping
    public ResponseEntity<List<Task>> readAll() {
        return ResponseEntity.ok(service.readAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> read(@PathVariable int id) {
        return service.isValid(id) ? ResponseEntity.ok(service.read(id)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        return ResponseEntity.ok(service.create(task));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Task task) {
        if (service.isValid(id)) {
            service.update(id, task);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (service.isValid(id)) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
