package ua.edu.chnu.tasks_api.tasks;

import java.time.LocalDateTime;

public record TaskResponse(String name, String description, Long courseId, LocalDateTime deadline, Boolean completed,
                           Integer grade) {
}
