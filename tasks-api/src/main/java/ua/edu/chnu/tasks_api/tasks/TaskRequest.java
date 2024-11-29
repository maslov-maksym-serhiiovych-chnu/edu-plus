package ua.edu.chnu.tasks_api.tasks;

import java.time.LocalDateTime;

public record TaskRequest(String name, String description, Long courseId, LocalDateTime deadline) {
}
