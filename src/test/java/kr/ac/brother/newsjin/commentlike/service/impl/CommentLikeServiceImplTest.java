package kr.ac.brother.newsjin.commentlike.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.board.repository.BoardRepository;
import kr.ac.brother.newsjin.comment.entity.Comment;
import kr.ac.brother.newsjin.comment.repository.CommentRepository;
import kr.ac.brother.newsjin.commentlike.entity.CommentLike;
import kr.ac.brother.newsjin.commentlike.repository.CommentLikeRepository;
import kr.ac.brother.newsjin.commentlike.service.CommentLikeService;
import kr.ac.brother.newsjin.user.entity.User;
import kr.ac.brother.newsjin.user.entity.UserRole;
import kr.ac.brother.newsjin.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class CommentLikeServiceImplTest {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentLikeService commentLikeService;
    @Autowired
    CommentLikeRepository commentLikeRepository;

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

    public Board insertBoard(User user) {
        Board board = Board.builder()
            .title("testtitle")
            .content("testcontent")
            .user(user)
            .build();
        return boardRepository.save(board);
    }

    public Comment insertComment(User user, Board board) {
        Comment comment = Comment.builder()
            .content("test content")
            .user(user)
            .board(board)
            .build();
        return commentRepository.save(comment);
    }

    @Test
    @DisplayName("댓글에 좋아요를 누른다.")
    void likeComment() {
        // given
        User user = insertUser();
        Board board = insertBoard(user);
        Comment comment = insertComment(user, board);

        Long commentId = comment.getId();

        // when
        commentLikeService.likeComment(commentId, user);

        // then
        Optional<CommentLike> findCommentLike = commentLikeRepository.findByUserAndCommentId(user,
            commentId);
        assertThat(findCommentLike).isNotEmpty();

        CommentLike commentLike = findCommentLike.get();
        assertThat(commentLike.getComment().getId()).isEqualTo(comment.getId());
        assertThat(commentLike.getUser().getId()).isEqualTo(user.getId());
    }

    @Test
    @DisplayName("댓글에 좋아요를 취소한다.")
    void unlikeComment() {
        // given
        User user = insertUser();
        Board board = insertBoard(user);
        Comment comment = insertComment(user, board);

        Long commentId = comment.getId();
        commentLikeService.likeComment(commentId, user);

        // when
        commentLikeService.unlikeComment(commentId, user);

        // then
        Optional<CommentLike> findCommentLike = commentLikeRepository.findByUserAndCommentId(user,
            commentId);
        assertThat(findCommentLike).isEmpty();
    }
}