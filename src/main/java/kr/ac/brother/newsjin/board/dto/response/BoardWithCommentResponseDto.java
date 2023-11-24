package kr.ac.brother.newsjin.board.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import kr.ac.brother.newsjin.comment.dto.response.CommentWithUserResponseDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardWithCommentResponseDto {

    private Long id;
    private String title;
    private String content;
    private String attachment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentWithUserResponseDTO> comments;
}