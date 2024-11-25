package ua.edu.chnu.comments_api.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository repository;

    public Comment create(Comment comment) {
        return repository.save(comment);
    }

    public List<Comment> readAll() {
        return repository.findAll();
    }

    public Comment read(int id) {
        return repository.findById(id).orElse(null);
    }

    public void update(int id, Comment comment) {
        if (isValid(id)) {
            comment.setId(id);
            repository.save(comment);
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
