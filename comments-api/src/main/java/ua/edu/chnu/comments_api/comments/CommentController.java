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
    public ResponseEntity<List<CommentResponse>> readAll() {
        var comments = service.readAll()
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentResponse> read(@PathVariable Long id) {
        Comment comment = service.read(id);
        return comment == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toResponse(comment));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody CommentRequest comment) {
        Comment created = service.create(toModel(comment));
        return ResponseEntity.ok(toResponse(created));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CommentRequest comment) {
        return service.update(id, toModel(comment)) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    private CommentResponse toResponse(Comment comment) {
        return new CommentResponse(comment.getComment(), comment.getTargetType(), comment.getTargetId(), comment.getCreatedAt());
    }

    private Comment toModel(CommentRequest request) {
        return new Comment(request.comment(), request.targetType(), request.targetId());
    }
}
