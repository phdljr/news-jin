package kr.ac.brother.newsjin.user.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import kr.ac.brother.newsjin.user.dto.request.SignUpRequestDto;
import kr.ac.brother.newsjin.user.dto.response.SignUpResponseDto;
import kr.ac.brother.newsjin.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    @Transactional
    @DisplayName("회원가입을 테스트한다.")
    public void singUpTest() {
        // given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .username("usernametest")
            .email("emailtest")
            .password("passwordtest")
            .nickname("nicknametest")
            .build();

        // when
        SignUpResponseDto result = userService.signUp(dto);

        // then
        assertThat(result).isNotNull();
    }
}