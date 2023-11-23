package kr.ac.brother.newsjin.comment.service.impl;

import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.board.repository.BoardRepository;
import kr.ac.brother.newsjin.comment.dto.request.CommentRequestDTO;
import kr.ac.brother.newsjin.comment.dto.response.CommentResponseDTO;
import kr.ac.brother.newsjin.comment.entity.Comment;
import kr.ac.brother.newsjin.comment.exception.NotFoundCommentException;
import kr.ac.brother.newsjin.comment.exception.NotMatchCommentException;
import kr.ac.brother.newsjin.comment.repository.CommentRepository;
import kr.ac.brother.newsjin.comment.service.CommentService;
import kr.ac.brother.newsjin.user.entity.User;
import kr.ac.brother.newsjin.user.exception.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    // CommentRepository 인스턴스를 주입받음 (생성자 주입)
    private final CommentRepository commentRepository;
    // 게시글을 가져오기위해 BoardRepository 와 연결
    private final BoardRepository boardRepository;

    @Override
    // 댓글을 생성하는 메서드 (매개변수로 클라이언트 요청, 유저데이터, 게시글 Id)
    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO, User user, Long boardId) {
        // board 에 넣어줄  boardRepository 안에 있는 boardId 를 찾아서(findById) 가져와라(get)
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundCommentException::new);
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

    // ACID 를 보장해줌, 서비스 메서드에서 데이터베이스 작업을 수행하는 중간에 예외가 발생하면 해당 메서드의 모든 변경 내용이 롤백되어 이전상태로 돌아가는데
    // 데이터 일관성을 유지하는데 도움이 됨, 여러 개의 데이터베이스 작업이 하나의 논리적 단위로 묶일 때 유용
    @Transactional
    @Override
    // 댓글을 수정하는 메서드 (매개변수로 클라이언트 요청, 유저데이터, 게시글 Id, 댓글 Id)
    public CommentResponseDTO modifyComment(CommentRequestDTO commentRequestDTO, User user, Long boardId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotMatchCommentException::new);
        // Board board = boardRepository.findById(boardId).orElseThrow(NotFoundUserException::new); 아마 필요 없을거 같음..

        // userId 가 comment.User 의 id와 일치하지 않다면
        if (!user.getId().equals(comment.getUser().getId())) {
            // 예외 처리를 시켜 NotMatchCommentException 메소드를 실행
            throw new NotMatchCommentException();
        }
        // 일치한다면 if문이 실행되지 않고 이어서 실행
        // comment 를 수정함
        comment.modify(commentRequestDTO);

        return new CommentResponseDTO(comment);
    }
}
