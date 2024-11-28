package ua.edu.chnu.comments_api.comments;

public record CommentRequest(String comment, TargetType targetType, Long targetId) {
}
