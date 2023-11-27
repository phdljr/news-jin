package kr.ac.brother.newsjin.comment.dto.response;

import java.time.LocalDateTime;
import kr.ac.brother.newsjin.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentWithUserResponseDTO {

    private final Long id;
    private final String nickname;
    private final String content;
    private final Long likes;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentWithUserResponseDTO(Comment comment, Long likes) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreateAt();
        this.modifiedAt = comment.getModifiedAt();
        this.nickname = comment.getUser() == null ? "탈퇴한 사용자" : comment.getUser().getNickname();
        this.likes = likes;
    }
}
