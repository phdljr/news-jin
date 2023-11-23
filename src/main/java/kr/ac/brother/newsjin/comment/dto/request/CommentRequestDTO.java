package kr.ac.brother.newsjin.comment.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {
    // 클라이언트가 보내는 요청 목록(실제로 작성할 내용)
    private String content;
}
