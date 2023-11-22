package kr.ac.brother.newsjin.user.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import kr.ac.brother.newsjin.user.dto.request.IntroRequestDto;
import kr.ac.brother.newsjin.user.dto.request.NicknameRequestDto;
import kr.ac.brother.newsjin.user.dto.request.SignUpRequestDto;
import kr.ac.brother.newsjin.user.dto.response.IntroResponseDto;
import kr.ac.brother.newsjin.user.dto.response.NicknameResponseDto;
import kr.ac.brother.newsjin.user.dto.response.SignUpResponseDto;
import kr.ac.brother.newsjin.user.dto.response.UserResponseDto;
import kr.ac.brother.newsjin.user.entity.User;
import kr.ac.brother.newsjin.user.entity.UserRole;
import kr.ac.brother.newsjin.user.exception.NotFoundUserException;
import kr.ac.brother.newsjin.user.repository.UserRepository;
import kr.ac.brother.newsjin.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    private User insertUser() {
        User user = User.builder()
            .username("testusername")
            .password(passwordEncoder.encode("testpassword"))
            .email("test@email.com")
            .image("testimage")
            .intro("testintro")
            .nickname("testnickname")
            .role(UserRole.USER)
            .build();
        return userRepository.save(user);
    }

    @Test
    @DisplayName("회원가입을 테스트한다.")
    public void singUpTest() {
        // given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .username("usernametest")
            .email("email@test.com")
            .password("passwordtest")
            .nickname("nicknametest")
            .build();

        // when
        SignUpResponseDto result = userService.signUp(dto);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("마이페이지를 조회한다.")
    public void myPageTest() {
        // given
        User user = insertUser();

        // when
        UserResponseDto responseDto = userService.getUserData(user);

        // then
        assertThat(responseDto).isNotNull();
    }

    @Test
    @DisplayName("닉네임을 수정한다.")
    public void updateNicknameTest() {
        // given
        User user = insertUser();
        String changeNickname = "change nickname";
        NicknameRequestDto requestDto = NicknameRequestDto.builder()
            .nickname(changeNickname)
            .build();

        // when
        NicknameResponseDto responseDto = userService.updateNickname(user, requestDto);

        // then
        User changedUser = userRepository.findById(user.getId())
            .orElseThrow(NotFoundUserException::new);

        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getNickname()).isEqualTo(changeNickname);
        assertThat(changedUser.getNickname()).isEqualTo(changeNickname);
    }

    @Test
    @DisplayName("한 줄 소개를 수정한다.")
    public void updateIntroTest() {
        // given
        User user = insertUser();
        String changeIntro = "change intro";
        IntroRequestDto requestDto = IntroRequestDto.builder()
            .intro(changeIntro)
            .build();

        // when
        IntroResponseDto responseDto = userService.updateIntro(user, requestDto);

        // then
        User changedUser = userRepository.findById(user.getId())
            .orElseThrow(NotFoundUserException::new);

        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getIntro()).isEqualTo(changeIntro);
        assertThat(changedUser.getIntro()).isEqualTo(changeIntro);
    }
}
