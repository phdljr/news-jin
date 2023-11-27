package kr.ac.brother.newsjin.comment.dto.response;

import java.time.LocalDateTime;
import kr.ac.brother.newsjin.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDTO {

    // 서버에서 반환해줘야 하는 목록 (클라이언트는 내용만 입력하여 보내면 되지만 실제로 보이게 되는건 댓글에 대한 id(이건 안보임), 클라이언트가 작성한 내용, 작성시간, 수정하게되면 수정시간을 나타내야해서 반환함)
    private final Long id;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    // 데이터 전달 용도 (데이터를 전송하거나 반환하는데 사용, 클라이언트와 서버간의 데이터 교환을 단순화 하고자 코드작성)
    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreateAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
