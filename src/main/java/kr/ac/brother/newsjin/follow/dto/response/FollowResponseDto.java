package kr.ac.brother.newsjin.follow.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowResponseDto {
    private Long userId;
    private String username;
    private String nickname;
}
