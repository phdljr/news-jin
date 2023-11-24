package kr.ac.brother.newsjin.comment.dto.response;

import java.time.LocalDateTime;
import kr.ac.brother.newsjin.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentWithUserResponseDTO {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String nickname;

    public CommentWithUserResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreateAt();
        this.modifiedAt = comment.getModifiedAt();
        this.nickname = comment.getUser().getNickname();
    }
}
