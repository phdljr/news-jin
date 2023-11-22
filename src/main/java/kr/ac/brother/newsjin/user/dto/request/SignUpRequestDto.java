package kr.ac.brother.newsjin.user.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private String username;
    private String password;
    private String email;
    private String nickname;

    @Builder
    public SignUpRequestDto(final String username, final String password, final String email,
        final String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }
}
