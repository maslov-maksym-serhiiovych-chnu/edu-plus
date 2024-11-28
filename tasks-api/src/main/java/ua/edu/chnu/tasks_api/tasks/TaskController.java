package ua.edu.chnu.tasks_api.tasks;

import org.springframework.data.domain.Sort;
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
    public ResponseEntity<List<TaskDTO>> readAll(@RequestParam(required = false) Long courseId,
                                                 @RequestParam(required = false) String name,
                                                 @RequestParam(required = false) boolean completed,
                                                 @RequestParam(required = false) String sortBy,
                                                 @RequestParam(required = false) Sort.Direction direction) {
        var tasks = service.readAll(courseId, name, completed, sortBy, direction)
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
        return new TaskDTO(task.getName(), task.getDescription(), task.getCourseId(), task.getDeadline(), task.isCompleted());
    }

    private Task toModel(TaskDTO task) {
        return new Task(task.name(), task.description(), task.courseId(), task.deadline(), task.completed());
    }
}
