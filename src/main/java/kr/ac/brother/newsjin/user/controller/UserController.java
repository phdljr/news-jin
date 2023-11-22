package kr.ac.brother.newsjin.user.controller;

import kr.ac.brother.newsjin.user.dto.request.SignUpRequestDto;
import kr.ac.brother.newsjin.user.dto.response.SignUpResponseDto;
import kr.ac.brother.newsjin.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/users/sign-up")
    public ResponseEntity<SignUpResponseDto> signUp(
        @RequestBody SignUpRequestDto signUpRequestDto
    ) {
        SignUpResponseDto responseDto = userService.signUp(signUpRequestDto);
        return ResponseEntity.ok(responseDto);
    }
}