package ua.edu.chnu.tasks_api.tasks;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "course_id"}))
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
    private Boolean completed = false;

    @Column
    private Integer grade;

    public Task() {
    }

    public Task(String name, String description, Long courseId, LocalDateTime deadline) {
        this.name = name;
        this.description = description;
        this.courseId = courseId;
        this.deadline = deadline;
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

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        if (grade < 0 || grade > 100) {
            return;
        }
        this.grade = grade;
    }
}
