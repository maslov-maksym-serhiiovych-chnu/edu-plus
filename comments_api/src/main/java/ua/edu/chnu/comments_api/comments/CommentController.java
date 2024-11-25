package ua.edu.chnu.comments_api.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/comments")
public class CommentController {
    private final CommentService service;

    @GetMapping
    public ResponseEntity<List<Comment>> readAll() {
        return ResponseEntity.ok(service.readAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> read(@PathVariable int id) {
        return service.isValid(id) ? ResponseEntity.ok(service.read(id)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody Comment comment) {
        return ResponseEntity.ok(service.create(comment));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Comment comment) {
        if (service.isValid(id)) {
            service.update(id, comment);
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
