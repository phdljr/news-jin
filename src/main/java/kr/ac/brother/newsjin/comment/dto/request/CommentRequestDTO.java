package kr.ac.brother.newsjin.comment.dto.request;

import lombok.Getter;

@Getter
public class CommentRequestDTO {
    // 클라이언트가 보내는 요청 목록(사용자가 실제로 작성할 내용)
    private String content;
}
