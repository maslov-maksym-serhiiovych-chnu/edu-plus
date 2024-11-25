package ua.edu.chnu.tasks_api.tasks;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "content", nullable = false, length = Integer.MAX_VALUE)
    private String content;
}