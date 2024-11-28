package ua.edu.chnu.tasks_api.tasks;

import jakarta.persistence.*;

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

    public Task() {
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
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
}
