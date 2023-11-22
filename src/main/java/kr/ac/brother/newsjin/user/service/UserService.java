package kr.ac.brother.newsjin.user.service;

import kr.ac.brother.newsjin.user.dto.request.SignUpRequestDto;
import kr.ac.brother.newsjin.user.dto.response.SignUpResponseDto;

public interface UserService {

    SignUpResponseDto signUp(SignUpRequestDto requestDto);
}
