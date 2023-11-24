package kr.ac.brother.newsjin.commentservice;

import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.board.repository.BoardRepository;
import kr.ac.brother.newsjin.comment.dto.response.CommentResponseDTO;
import kr.ac.brother.newsjin.comment.entity.Comment;
import kr.ac.brother.newsjin.comment.repository.CommentRepository;
import kr.ac.brother.newsjin.comment.service.CommentService;
import kr.ac.brother.newsjin.user.entity.User;
import kr.ac.brother.newsjin.user.entity.UserRole;
import kr.ac.brother.newsjin.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class CommentServiceImplTest {

    @Autowired
    CommentService commentService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardRepository boardRepository;

    // 사용자 정보 생성
    private User insertUser() {
        User user = User.builder()
                .username("testusername")
                .password(passwordEncoder.encode("testpassword"))
                .email("test@email.com")
                .intro("testintro")
                .nickname("testnickname")
                .role(UserRole.USER)
                .build();
        return userRepository.save(user);
    }

    // 게시물 정보 생성
    public Board insertBoard(User user) {
        Board board = Board.builder()
                .title("testtitle")
                .content("testcontent")
                .user(user)
                .build();
        return boardRepository.save(board);
    }

    // 댓글 정보 생성
    public Comment insertComment(Board board, User user) {
        Comment comment = Comment.builder()
                .content("testcontents")
                .user(user)
                .board(board)
                .build();
        return commentRepository.save(comment);
    }

    // 댓글 작성 기능 테스트
    @Test
    @DisplayName("댓글을 작성")
    public void createCommentTest() {
        // given
        // 테스트를 위한 초기 상태 설정
        User user = insertUser();
        Board board = insertBoard(user);
        Comment comment = insertComment(board, user);

        // when
        // 댓글 삭제 메서드 호출
        CommentResponseDTO responseDTO = commentService.deleteComment(user, comment.getId());

        // then
        // 댓글이 삭제 되었는지 확인
        Optional<Comment> removeComment = commentRepository.findById(comment.getId());

        assertThat(removeComment).isEmpty();
        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getId()).isEqualTo(comment.getId());
    }
}
