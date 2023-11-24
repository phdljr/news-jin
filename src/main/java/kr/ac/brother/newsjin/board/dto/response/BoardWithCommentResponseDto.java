package kr.ac.brother.newsjin.board.dto.response;

import kr.ac.brother.newsjin.comment.dto.response.CommentResponseDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class BoardWithCommentResponseDto {
    private Long id;
    private String title;
    private String content;
    private String attachment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDTO> comments;
}