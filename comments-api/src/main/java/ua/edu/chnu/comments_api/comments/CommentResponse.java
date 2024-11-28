package ua.edu.chnu.comments_api.comments;

import java.time.LocalDateTime;

public record CommentResponse(String comment, TargetType targetType, Long targetId, LocalDateTime createdAt) {
}
