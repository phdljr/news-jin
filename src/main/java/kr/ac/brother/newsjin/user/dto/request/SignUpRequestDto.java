package kr.ac.brother.newsjin.user.dto.request;

import lombok.Getter;

@Getter
public class SignUpRequestDto {
    private String username;
    private String password;
    private String email;
    private String nickname;
}
