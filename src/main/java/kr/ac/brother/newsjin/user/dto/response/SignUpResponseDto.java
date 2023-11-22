package kr.ac.brother.newsjin.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private Long id;
    private String username;
    private String email;
    private String nickname;

    @Builder
    public SignUpResponseDto(final Long id, final String username, final String email,
        final String nickname) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nickname = nickname;
    }
}
