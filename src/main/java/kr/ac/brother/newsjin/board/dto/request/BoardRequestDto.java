package kr.ac.brother.newsjin.board.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardRequestDto {

    private Long id;
    private String title;
    private String content;
    private String attachment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
