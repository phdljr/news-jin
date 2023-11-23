package kr.ac.brother.newsjin.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private String nickname;
    private String imagePath;
    private String intro;

    @Builder
    public UserResponseDto(final String nickname, final String imagePath, final String intro) {
        this.nickname = nickname;
        this.imagePath = imagePath;
        this.intro = intro;
    }
}
