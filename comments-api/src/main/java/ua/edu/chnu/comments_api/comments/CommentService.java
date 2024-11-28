package ua.edu.chnu.comments_api.comments;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.edu.chnu.comments_api.courses.CourseClient;
import ua.edu.chnu.comments_api.tasks.TaskClient;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository repository;
    private final CourseClient courseClient;
    private final TaskClient taskClient;

    public CommentService(CommentRepository repository, CourseClient courseClient, TaskClient taskClient) {
        this.repository = repository;
        this.courseClient = courseClient;
        this.taskClient = taskClient;
    }

    public Comment create(Comment comment) {
        return isTargetExisting(comment) ? repository.save(comment) : null;
    }

    public List<Comment> readAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Comment read(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(Long id, Comment comment) {
        if (read(id) == null || !isTargetExisting(comment)) {
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

    private boolean isTargetExisting(Comment comment) {
        return switch (comment.getTargetType()) {
            case COURSE -> Boolean.TRUE.equals(courseClient.isExisting(comment.getTargetId()).getBody());
            case TASK -> Boolean.TRUE.equals(taskClient.isExisting(comment.getTargetId()).getBody());
        };
    }
}
