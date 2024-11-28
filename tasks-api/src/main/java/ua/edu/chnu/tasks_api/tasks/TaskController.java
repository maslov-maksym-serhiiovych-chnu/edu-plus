package ua.edu.chnu.tasks_api.tasks;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> readAll() {
        var tasks = service.readAll()
                .stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDTO> read(@PathVariable Long id) {
        Task task = service.read(id);
        return task == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toDTO(task));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO task) {
        Task created = service.create(toModel(task));
        return created == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toDTO(created));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody TaskDTO task) {
        return service.update(id, toModel(task)) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("{id}/is-existing")
    public ResponseEntity<Boolean> isExisting(@PathVariable Long id) {
        return ResponseEntity.ok(service.isExisting(id));
    }

    private TaskDTO toDTO(Task task) {
        return new TaskDTO(task.getName(), task.getDescription(), task.getCourseId());
    }

    private Task toModel(TaskDTO task) {
        return new Task(task.name(), task.description(), task.courseId());
    }
}
