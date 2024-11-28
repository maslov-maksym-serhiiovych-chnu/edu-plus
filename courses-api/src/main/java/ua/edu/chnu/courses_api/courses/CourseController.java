package ua.edu.chnu.courses_api.courses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> readAll() {
        var courses = service.readAll()
                .stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("{id}")
    public ResponseEntity<CourseDTO> read(@PathVariable Long id) {
        Course course = service.read(id);
        return course == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toDTO(course));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> create(@RequestBody CourseDTO course) {
        Course created = service.create(toModel(course));
        return ResponseEntity.ok(toDTO(created));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CourseDTO course) {
        return service.update(id, toModel(course)) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("{id}/is-existing")
    public ResponseEntity<Boolean> isExisting(@PathVariable Long id) {
        return ResponseEntity.ok(service.isExisting(id));
    }
    
    private CourseDTO toDTO(Course course) {
        return new CourseDTO(course.getName(), course.getDescription());
    }

    private Course toModel(CourseDTO course) {
        return new Course(course.name(), course.description());
    }
}
