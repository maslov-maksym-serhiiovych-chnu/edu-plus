package ua.edu.chnu.comments_api.comments;

public record CommentDTO(String comment, TargetType targetType, Long targetId) {
}
