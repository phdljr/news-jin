package kr.ac.brother.newsjin.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String nickname;
    private String image;
    private String intro;

    @Builder
    public UserResponseDto(final String nickname, final String image, final String intro) {
        this.nickname = nickname;
        this.image = image;
        this.intro = intro;
    }
}
