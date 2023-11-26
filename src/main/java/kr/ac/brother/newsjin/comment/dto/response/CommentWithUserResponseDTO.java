package kr.ac.brother.newsjin.comment.dto.response;

import java.time.LocalDateTime;
import kr.ac.brother.newsjin.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentWithUserResponseDTO {

    private Long id;
    private String nickname;
    private String content;
    private Long likes;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentWithUserResponseDTO(Comment comment, Long likes) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreateAt();
        this.modifiedAt = comment.getModifiedAt();
        this.nickname = comment.getUser() == null ? "탈퇴한 사용자" : comment.getUser().getNickname();
        this.likes = likes;
    }
}
