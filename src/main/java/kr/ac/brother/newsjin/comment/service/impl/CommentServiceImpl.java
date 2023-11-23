package kr.ac.brother.newsjin.comment.service.impl;

import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.board.repository.BoardRepository;
import kr.ac.brother.newsjin.comment.dto.request.CommentRequestDTO;
import kr.ac.brother.newsjin.comment.dto.response.CommentResponseDTO;
import kr.ac.brother.newsjin.comment.entity.Comment;
import kr.ac.brother.newsjin.comment.repository.CommentRepository;
import kr.ac.brother.newsjin.comment.service.CommentService;
import kr.ac.brother.newsjin.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    // CommentRepository 인스턴스를 주입받음 (생성자 주입)
    private final CommentRepository commentRepository;
    // 게시글을 가져오기위해 BoardRepository 와 연결
    private final BoardRepository boardRepository;

    // 댓글을 생성하는 메서드 (매개변수로 클라이언트 요청, 유저데이터, 게시글 Id)
    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO, User user, Long boardId) {
        // board 에 넣어줄  boardRepository 안에 있는 boardId 를 찾아서(findById) 가져와라(get)
        Board board = boardRepository.findById(boardId).get();
        // Comment 엔터티를 빌더 패턴을 사용하여 생성
        Comment comment = Comment.builder()
                .content(commentRequestDTO.getContent())
                .user(user)
                .board(board)
                .build();

        // 생성한 Comment 엔터티를 저장하고 저장된 엔터티를 받아옴
        comment = commentRepository.save(comment);

        // 저장된 Comment 엔터티를 이용하여 CommentResponseDTO를 생성하고 반환
        return new CommentResponseDTO(comment);
    }
}
