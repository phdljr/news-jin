package kr.ac.brother.newsjin.user.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PasswordRequestDto {

    private String currentPassword;
    private String newPassword;
    private String checkNewPassword;
}
