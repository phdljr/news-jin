package kr.ac.brother.newsjin.user.controller;

import kr.ac.brother.newsjin.global.security.userdetails.UserDetailsImpl;
import kr.ac.brother.newsjin.user.dto.request.NicknameRequestDto;
import kr.ac.brother.newsjin.user.dto.request.SignUpRequestDto;
import kr.ac.brother.newsjin.user.dto.response.NicknameResponseDto;
import kr.ac.brother.newsjin.user.dto.response.SignUpResponseDto;
import kr.ac.brother.newsjin.user.dto.response.UserResponseDto;
import kr.ac.brother.newsjin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponseDto> signUp(
        @RequestBody SignUpRequestDto signUpRequestDto
    ) {
        SignUpResponseDto responseDto = userService.signUp(signUpRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/")
    public ResponseEntity<UserResponseDto> myPage(
        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl
    ) {
        UserResponseDto responseDto = userService.getUserData(userDetailsImpl.getUser());
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/nickname")
    public ResponseEntity<NicknameResponseDto> updateNickname(
        @RequestBody NicknameRequestDto nicknameRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        NicknameResponseDto responseDto = userService.updateNickname(userDetails.getUser(),
            nicknameRequestDto);
        return ResponseEntity.ok(responseDto);
    }
}