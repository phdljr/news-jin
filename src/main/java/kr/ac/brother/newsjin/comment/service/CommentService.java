package kr.ac.brother.newsjin.comment.service;

import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.comment.dto.request.CommentRequestDTO;
import kr.ac.brother.newsjin.comment.dto.response.CommentResponseDTO;
import kr.ac.brother.newsjin.user.entity.User;

public interface CommentService {
    // createComment 라는 메서드 선언, 댓글을 생성할 때 사용 | (CommentRequestDTO, User, Board) 객체를 매개변수로 받아서 CommentResponseDTO 를 반환
    // CommentRequestDTO : 댓글 생성 시 필요한 정보를 담고 있는 DTO
    // User : 댓글을 생성하는 사용자의 정보를 담고 있는 Entity
    // Board : 댓글이 작성되는 게시판의 정보를 담고 있는 Entity (필요한가..? 필요한거 같기도 하고 아닌거 같기도 하고)
    CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO, User user, Long boardId);
}
