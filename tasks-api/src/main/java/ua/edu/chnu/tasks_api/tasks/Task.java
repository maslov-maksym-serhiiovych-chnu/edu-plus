package ua.edu.chnu.tasks_api.tasks;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 5000)
    private String description;

    @Column(nullable = false)
    private Long courseId;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(nullable = false)
    public boolean completed = false;

    public Task() {
    }

    public Task(String name, String description, Long courseId, LocalDateTime deadline, boolean completed) {
        this.name = name;
        this.description = description;
        this.courseId = courseId;
        this.deadline = deadline;
        this.completed = completed;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getCourseId() {
        return courseId;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public boolean isCompleted() {
        return completed;
    }
}
