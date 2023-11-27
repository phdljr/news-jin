package kr.ac.brother.newsjin.board.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoardRequestDto {

    private String title;
    private String content;
    private String attachment;
}
