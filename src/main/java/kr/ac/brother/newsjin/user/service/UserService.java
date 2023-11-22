package kr.ac.brother.newsjin.user.service;

import kr.ac.brother.newsjin.user.dto.request.SignUpRequestDto;
import kr.ac.brother.newsjin.user.dto.response.UserResponseDto;
import kr.ac.brother.newsjin.user.dto.response.SignUpResponseDto;
import kr.ac.brother.newsjin.user.entity.User;

public interface UserService {

    SignUpResponseDto signUp(SignUpRequestDto requestDto);

    UserResponseDto getUserData(User user);
}
