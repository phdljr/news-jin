package kr.ac.brother.newsjin.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NicknameResponseDto {

    private String username;
    private String nickname;
}
