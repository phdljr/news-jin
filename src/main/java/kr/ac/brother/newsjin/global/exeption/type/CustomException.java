package kr.ac.brother.newsjin.global.exeption.type;

import kr.ac.brother.newsjin.global.exeption.dto.ExceptionResponseDto;
import lombok.Getter;

@Getter
public enum CustomException {
    NOT_FOUND_CARD(404, "요청하신 할일 카드가 존재하지 않습니다."),
    NOT_FOUND_COMMENT(404, "요청하신 댓글이 존재하지 않습니다."),
    NOT_MATCH_USER(400, "작성자만 수정/삭제할 수 있습니다."),
    NOT_VALID_TOKEN(400, "유효하지 않는 JWT입니다."),
    EXPIRED_TOKEN(400, "만료된 JWT입니다."),
    UNSUPPORTED_TOKEN(400, "지원하지 않는 JWT입니다."),
    EMPTY_TOKEN(400, "JWT가 비어있습니다."),
    AUTHENTICATION_ERROR(400, "잘못된 인증입니다."),
    DUPLICATE_USERNAME(400, "중복된 username입니다."),
    DUPLICATE_EMAIL(400, "중복된 email입니다."),
    PRIVATE_CARD_ACCESS(400, "작성자만 조회할 수 있습니다."),
    BAD_LOGIN(400, "회원을 찾을 수 없습니다.");

    private int statusCode;
    private String message;

    CustomException(final int statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ExceptionResponseDto toDto() {
        return ExceptionResponseDto.builder()
            .statusCode(statusCode)
            .message(message)
            .build();
    }
}

