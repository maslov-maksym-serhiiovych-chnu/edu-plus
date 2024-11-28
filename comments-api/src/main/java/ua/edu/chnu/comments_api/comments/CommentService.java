package ua.edu.chnu.comments_api.comments;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public Comment create(Comment comment) {
        return repository.save(comment);
    }

    public List<Comment> readAll() {
        return repository.findAll();
    }

    public Comment read(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(Long id, Comment comment) {
        if (read(id) == null) {
            return false;
        }

        comment.setId(id);
        repository.save(comment);
        return true;
    }

    public boolean delete(Long id) {
        Comment comment = read(id);
        if (comment == null) {
            return false;
        }

        repository.delete(comment);
        return true;
    }
}
