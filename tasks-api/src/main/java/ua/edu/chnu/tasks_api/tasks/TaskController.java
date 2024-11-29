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
    public ResponseEntity<List<TaskResponse>> readAll(@RequestParam(required = false) Long courseId,
                                                      @RequestParam(required = false) String name,
                                                      @RequestParam(required = false) Boolean completed,
                                                      @RequestParam(required = false) SortBy sortBy,
                                                      @RequestParam(required = false) Sort.Direction direction) {
        var tasks = service.readAll(courseId, name, completed, sortBy, direction)
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskResponse> read(@PathVariable Long id) {
        Task task = service.read(id);
        return task == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toResponse(task));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody TaskRequest task) {
        Task created = service.create(toModel(task));
        return created == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toResponse(created));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody TaskRequest task) {
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

    @PutMapping("{id}/set-completed")
    public ResponseEntity<Void> setCompleted(@PathVariable Long id) {
        return service.setCompleted(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("{id}/set-grade")
    public ResponseEntity<Void> setGrade(@PathVariable Long id, @RequestParam Integer grade) {
        return service.setGrade(id, grade) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(task.getName(), task.getDescription(), task.getCourseId(), task.getDeadline(),
                task.isCompleted(), task.getGrade());
    }

    private Task toModel(TaskRequest task) {
        return new Task(task.name(), task.description(), task.courseId(), task.deadline());
    }
}
