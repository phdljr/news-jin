package kr.ac.brother.newsjin.board.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import kr.ac.brother.newsjin.board.dto.response.BoardResponseDto;
import kr.ac.brother.newsjin.board.dto.response.BoardWithCommentResponseDto;
import kr.ac.brother.newsjin.board.entity.Board;
import kr.ac.brother.newsjin.board.repository.BoardRepository;
import kr.ac.brother.newsjin.board.service.BoardService;
import kr.ac.brother.newsjin.user.entity.User;
import kr.ac.brother.newsjin.user.entity.UserRole;
import kr.ac.brother.newsjin.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    private User insertUser() {
        User user = User.builder()
            .username("testusername")
            .password(passwordEncoder.encode("testpassword"))
            .email("test@email.com")
            .imageName("testimage")
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
    public void deleteBoardTest() {

        // given
        User user = insertUser();
        Board board = insertBoard(user);

        // when
        BoardResponseDto responseDto = boardService.deleteBoard(board.getId(), user);

        // then
        Optional<Board> removedBoard = boardRepository.findById(board.getId());

        assertThat(removedBoard).isEmpty();
        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getId()).isEqualTo(board.getId());

    }

    @Test
    public void getBoardTest() {
        // given
        User user = insertUser();
        Board board = insertBoard(user);

        // when
        BoardWithCommentResponseDto responseDto = boardService.getBoard(board.getId());

        // then
        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getId()).isEqualTo(board.getId());
    }

}