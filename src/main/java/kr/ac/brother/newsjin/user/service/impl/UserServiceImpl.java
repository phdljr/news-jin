package kr.ac.brother.newsjin.user.service.impl;

import java.util.Optional;
import kr.ac.brother.newsjin.user.dto.request.SignUpRequestDto;
import kr.ac.brother.newsjin.user.dto.response.UserResponseDto;
import kr.ac.brother.newsjin.user.dto.response.SignUpResponseDto;
import kr.ac.brother.newsjin.user.entity.User;
import kr.ac.brother.newsjin.user.entity.UserRole;
import kr.ac.brother.newsjin.user.exception.AlreadyExistEmailException;
import kr.ac.brother.newsjin.user.exception.AlreadyExistNicknameException;
import kr.ac.brother.newsjin.user.exception.AlreadyExistUsernameException;
import kr.ac.brother.newsjin.user.repository.UserRepository;
import kr.ac.brother.newsjin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpResponseDto signUp(final SignUpRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new AlreadyExistUsernameException();
        }

        // email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new AlreadyExistEmailException();
        }

        // nickname 중복확인
        String nickname = requestDto.getNickname();
        Optional<User> checkNickname = userRepository.findByNickname(nickname);
        if (checkNickname.isPresent()) {
            throw new AlreadyExistNicknameException();
        }

        // 사용자 등록
        User user = User.builder()
            .username(username)
            .password(password)
            .email(email)
            .nickname(requestDto.getNickname())
            .role(UserRole.USER)
            .build();
        user = userRepository.save(user);

        return SignUpResponseDto.builder()
            .id(user.getId())
            .username(username)
            .nickname(nickname)
            .email(email)
            .build();
    }

    @Override
    public UserResponseDto getUserData(final User user) {
        return UserResponseDto.builder()
            .nickname(user.getNickname())
            .image(user.getImage())
            .intro(user.getIntro())
            .build();
    }
}