package kr.ac.brother.newsjin.board.dto.response;

import java.time.LocalDateTime;
import kr.ac.brother.newsjin.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private String attachment;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreateAt();
        this.modifiedAt = board.getModifiedAt();
    }

}
