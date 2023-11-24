package kr.ac.brother.newsjin.boardlike.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import javax.swing.text.html.Option;
import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.board.repository.BoardRepository;
import kr.ac.brother.newsjin.boardlike.entity.BoardLike;
import kr.ac.brother.newsjin.boardlike.repository.BoardLikeRepository;
import kr.ac.brother.newsjin.boardlike.service.BoardLikeService;
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
class BoardLikeServiceImplTest {
    @Autowired
    BoardLikeService boardLikeService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardLikeRepository boardLikeRepository;

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

    @Test
    @DisplayName("게시글에 좋아요를 누른다.")
    public void likeBoardService(){
        // given
        User user = insertUser();
        Board board = insertBoard(user);

        Long boardId = board.getId();

        // when
        boardLikeService.likeBoard(boardId, user);

        // then
        BoardLike boardLike = boardLikeRepository.findByUser(user).get();

        assertThat(boardLike.getBoard().getId()).isEqualTo(boardId);
        assertThat(boardLike.getUser().getId()).isEqualTo(user.getId());
    }

    @Test
    @DisplayName("게시글에 좋아요를 취소한다.")
    public void unlikeBoardService(){
        // given
        User user = insertUser();
        Board board = insertBoard(user);

        Long boardId = board.getId();
        boardLikeService.likeBoard(boardId, user);

        // when
        boardLikeService.unlikeBoard(boardId, user);

        // then
        Optional<BoardLike> boardLike = boardLikeRepository.findByUser(user);

        assertThat(boardLike.isEmpty()).isEqualTo(true);
    }
}