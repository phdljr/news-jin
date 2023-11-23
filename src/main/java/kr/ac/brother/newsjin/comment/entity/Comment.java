package kr.ac.brother.newsjin.comment.entity;

import jakarta.persistence.*;
import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.comment.dto.request.CommentRequestDTO;
import kr.ac.brother.newsjin.global.entity.BaseEntity;
import kr.ac.brother.newsjin.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_COMMENT")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(final String content, final User user, final Board board) {
        this.content = content;
        this.user = user;
        this.board = board;
    }

    public void modifyContent(String content) {
        this.content = content;
    }
}
