package kr.ac.brother.newsjin.board.dto.response;

import kr.ac.brother.newsjin.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private String title;
    private String content;
    private String attachment;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
    }

}
