package kr.ac.brother.newsjin.user.service.impl;

import java.util.Optional;
import kr.ac.brother.newsjin.user.dto.request.IntroRequestDto;
import kr.ac.brother.newsjin.user.dto.request.NicknameRequestDto;
import kr.ac.brother.newsjin.user.dto.request.PasswordRequestDto;
import kr.ac.brother.newsjin.user.dto.request.SignUpRequestDto;
import kr.ac.brother.newsjin.user.dto.request.UserWithdrawRequestDto;
import kr.ac.brother.newsjin.user.dto.response.IntroResponseDto;
import kr.ac.brother.newsjin.user.dto.response.NicknameResponseDto;
import kr.ac.brother.newsjin.user.dto.response.SignUpResponseDto;
import kr.ac.brother.newsjin.user.dto.response.UserResponseDto;
import kr.ac.brother.newsjin.user.entity.User;
import kr.ac.brother.newsjin.user.entity.UserRole;
import kr.ac.brother.newsjin.user.exception.AlreadyExistEmailException;
import kr.ac.brother.newsjin.user.exception.AlreadyExistNicknameException;
import kr.ac.brother.newsjin.user.exception.AlreadyExistUsernameException;
import kr.ac.brother.newsjin.user.exception.NotFoundUserException;
import kr.ac.brother.newsjin.user.exception.NotMatchCheckPassword;
import kr.ac.brother.newsjin.user.exception.NotMatchConfirmationPhrase;
import kr.ac.brother.newsjin.user.exception.NotMatchCurrentPassword;
import kr.ac.brother.newsjin.user.repository.UserRepository;
import kr.ac.brother.newsjin.user.service.UserImageService;
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
    private final UserImageService userImageService;

    private static final String CONFIRMATION_PHRASE = "회원 탈퇴 진행을 확인했습니다.";

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
            .imagePath(user.getImagePath())
            .intro(user.getIntro())
            .build();
    }

    @Override
    @Transactional
    public NicknameResponseDto updateNickname(
        final User user,
        final NicknameRequestDto requestDto
    ) {
        Optional<User> findUser = userRepository.findByNickname(requestDto.getNickname());
        if (findUser.isPresent()) {
            throw new AlreadyExistNicknameException();
        }

        User loginUser = userRepository.findById(user.getId())
            .orElseThrow(NotFoundUserException::new);

        loginUser.updateNickname(requestDto.getNickname());

        return NicknameResponseDto.builder()
            .username(loginUser.getUsername())
            .nickname(loginUser.getNickname())
            .build();
    }

    @Override
    @Transactional
    public IntroResponseDto updateIntro(final User user, final IntroRequestDto requestDto) {
        User loginUser = userRepository.findById(user.getId())
            .orElseThrow(NotFoundUserException::new);

        loginUser.updateIntro(requestDto.getIntro());

        return IntroResponseDto.builder()
            .username(loginUser.getUsername())
            .intro(loginUser.getIntro())
            .build();
    }

    @Override
    @Transactional
    public void updatePassword(final User user, final PasswordRequestDto passwordRequestDto) {
        User loginUser = userRepository.findById(user.getId())
            .orElseThrow(NotFoundUserException::new);

        if (!passwordEncoder.matches(passwordRequestDto.getCurrentPassword(),
            loginUser.getPassword())) {
            throw new NotMatchCurrentPassword();
        }

        if (!passwordRequestDto.getNewPassword().equals(passwordRequestDto.getCheckNewPassword())) {
            throw new NotMatchCheckPassword();
        }

        loginUser.updatePassword(passwordEncoder.encode(passwordRequestDto.getNewPassword()));
    }

    @Override
    public void withdraw(final UserWithdrawRequestDto userWithdrawRequestDto, final User user) {
        User loginUser = userRepository.findById(user.getId())
            .orElseThrow(NotFoundUserException::new);

        if (!passwordEncoder.matches(userWithdrawRequestDto.getPassword(), user.getPassword())) {
            throw new NotMatchCurrentPassword();
        }

        if (!userWithdrawRequestDto.getConfirmationPhrase().equals(CONFIRMATION_PHRASE)) {
            throw new NotMatchConfirmationPhrase();
        }

        userImageService.deleteImage(user);
        userRepository.delete(loginUser);
    }
}