package kr.ac.brother.newsjin.board.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardWithoutCommentResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long likes;
    private Long follows;
}
