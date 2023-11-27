package kr.ac.brother.newsjin.board.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardWithoutCommentResponseDto {

    private Long id;
    private String title;
    private String nickname;
    private Long likes;
    private LocalDateTime createdAt;
}
