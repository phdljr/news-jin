package kr.ac.brother.newsjin.user.service;

import kr.ac.brother.newsjin.user.dto.request.IntroRequestDto;
import kr.ac.brother.newsjin.user.dto.request.NicknameRequestDto;
import kr.ac.brother.newsjin.user.dto.request.SignUpRequestDto;
import kr.ac.brother.newsjin.user.dto.response.IntroResponseDto;
import kr.ac.brother.newsjin.user.dto.response.NicknameResponseDto;
import kr.ac.brother.newsjin.user.dto.response.UserResponseDto;
import kr.ac.brother.newsjin.user.dto.response.SignUpResponseDto;
import kr.ac.brother.newsjin.user.entity.User;

public interface UserService {

    SignUpResponseDto signUp(SignUpRequestDto requestDto);

    UserResponseDto getUserData(User user);

    NicknameResponseDto updateNickname(User user, NicknameRequestDto requestDto);

    IntroResponseDto updateIntro(User user, IntroRequestDto requestDto);
}
