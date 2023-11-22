package kr.ac.brother.newsjin.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NicknameRequestDto {
    private String nickname;
}
