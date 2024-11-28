package ua.edu.chnu.comments_api.comments;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentController {
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> readAll() {
        var comments = service.readAll()
                .stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentDTO> read(@PathVariable Long id) {
        Comment comment = service.read(id);
        return comment == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toDTO(comment));
    }

    @PostMapping
    public ResponseEntity<CommentDTO> create(@RequestBody CommentDTO comment) {
        Comment created = service.create(toModel(comment));
        return ResponseEntity.ok(toDTO(created));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CommentDTO comment) {
        return service.update(id, toModel(comment)) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    private CommentDTO toDTO(Comment comment) {
        return new CommentDTO(comment.getComment());
    }

    private Comment toModel(CommentDTO comment) {
        return new Comment(comment.comment());
    }
}
