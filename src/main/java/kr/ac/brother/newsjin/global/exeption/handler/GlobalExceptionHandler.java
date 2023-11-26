package kr.ac.brother.newsjin.global.exeption.handler;

import kr.ac.brother.newsjin.board.exception.IllegalBoardTypeException;
import kr.ac.brother.newsjin.board.exception.NotFoundBoardException;
import kr.ac.brother.newsjin.board.exception.NotMatchUserException;
import kr.ac.brother.newsjin.boardlike.exception.AlreadyExistBoardLikeException;
import kr.ac.brother.newsjin.boardlike.exception.NotFoundBoardLikeException;
import kr.ac.brother.newsjin.comment.exception.NotFoundCommentException;
import kr.ac.brother.newsjin.comment.exception.NotMatchCommentException;
import kr.ac.brother.newsjin.commentlike.exception.AlreadyExistCommentLikeException;
import kr.ac.brother.newsjin.commentlike.exception.NotFoundCommentLikeException;
import kr.ac.brother.newsjin.follow.exception.AlreadyExistFollowingUserException;
import kr.ac.brother.newsjin.follow.exception.NotAllowFollowingYourselfException;
import kr.ac.brother.newsjin.follow.exception.NotFoundFollowingUserException;
import kr.ac.brother.newsjin.global.exeption.dto.ExceptionResponseDto;
import kr.ac.brother.newsjin.global.exeption.type.CustomException;
import kr.ac.brother.newsjin.user.exception.AlreadyExistEmailException;
import kr.ac.brother.newsjin.user.exception.AlreadyExistNicknameException;
import kr.ac.brother.newsjin.user.exception.AlreadyExistUsernameException;
import kr.ac.brother.newsjin.user.exception.FailDeleteImageException;
import kr.ac.brother.newsjin.user.exception.FailUploadImageException;
import kr.ac.brother.newsjin.user.exception.NoImageFileException;
import kr.ac.brother.newsjin.user.exception.NoImageTypeException;
import kr.ac.brother.newsjin.user.exception.NotFoundUserException;
import kr.ac.brother.newsjin.user.exception.NotMatchCheckPassword;
import kr.ac.brother.newsjin.user.exception.NotMatchConfirmationPhrase;
import kr.ac.brother.newsjin.user.exception.NotMatchCurrentPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalBoardTypeException.class)
    public ResponseEntity<ExceptionResponseDto> handleIllegalBoardTypeException() {
        return ResponseEntity
            .status(CustomException.ILLEGAL_BOARD_TYPE.getStatusCode())
            .body(CustomException.ILLEGAL_BOARD_TYPE.toDto());
    }

    @ExceptionHandler(NotFoundBoardException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundBoardException() {
        return ResponseEntity
            .status(CustomException.NOT_FOUND_BOARD.getStatusCode())
            .body(CustomException.NOT_FOUND_BOARD.toDto());
    }

    @ExceptionHandler(NotMatchUserException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotMatchUserException() {
        return ResponseEntity
            .status(CustomException.NOT_MATCH_USER.getStatusCode())
            .body(CustomException.NOT_MATCH_USER.toDto());
    }

    @ExceptionHandler(AlreadyExistBoardLikeException.class)
    public ResponseEntity<ExceptionResponseDto> handleAlreadyExistBoardLikeException() {
        return ResponseEntity
            .status(CustomException.ALREADY_EXIST_BOARD_LIKE.getStatusCode())
            .body(CustomException.ALREADY_EXIST_BOARD_LIKE.toDto());
    }

    @ExceptionHandler(NotFoundBoardLikeException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundBoardLikeException() {
        return ResponseEntity
            .status(CustomException.NOT_FOUND_BOARD_LIKE.getStatusCode())
            .body(CustomException.NOT_FOUND_BOARD_LIKE.toDto());
    }

    @ExceptionHandler(NotFoundCommentException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundCommentException() {
        return ResponseEntity
            .status(CustomException.NOT_FOUND_COMMENT.getStatusCode())
            .body(CustomException.NOT_FOUND_COMMENT.toDto());
    }

    @ExceptionHandler(NotMatchCommentException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotMatchCommentException() {
        return ResponseEntity
            .status(CustomException.NOT_MATCH_COMMENT.getStatusCode())
            .body(CustomException.NOT_MATCH_COMMENT.toDto());
    }

    @ExceptionHandler(AlreadyExistCommentLikeException.class)
    public ResponseEntity<ExceptionResponseDto> handleAlreadyExistCommentLikeException() {
        return ResponseEntity
            .status(CustomException.ALREADY_EXIST_COMMENT_LIKE.getStatusCode())
            .body(CustomException.ALREADY_EXIST_COMMENT_LIKE.toDto());
    }

    @ExceptionHandler(NotFoundCommentLikeException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundCommentLikeException() {
        return ResponseEntity
            .status(CustomException.NOT_FOUND_COMMENT_LIKE.getStatusCode())
            .body(CustomException.NOT_FOUND_COMMENT_LIKE.toDto());
    }

    @ExceptionHandler(AlreadyExistFollowingUserException.class)
    public ResponseEntity<ExceptionResponseDto> handleAlreadyExistFollowingUserException() {
        return ResponseEntity
            .status(CustomException.ALREADY_EXISTS_FOLLOWING.getStatusCode())
            .body(CustomException.ALREADY_EXISTS_FOLLOWING.toDto());
    }

    @ExceptionHandler(NotAllowFollowingYourselfException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotAllowFollowingYourselfException() {
        return ResponseEntity
            .status(CustomException.NOT_ALLOW_FOLLOWING_YOURSELF.getStatusCode())
            .body(CustomException.NOT_ALLOW_FOLLOWING_YOURSELF.toDto());
    }

    @ExceptionHandler(NotFoundFollowingUserException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundFollowingUserException() {
        return ResponseEntity
            .status(CustomException.NOT_FOUND_FOLLOWING.getStatusCode())
            .body(CustomException.NOT_FOUND_FOLLOWING.toDto());
    }


    @ExceptionHandler(AlreadyExistEmailException.class)
    public ResponseEntity<ExceptionResponseDto> handleAlreadyExistEmailException() {
        return ResponseEntity
            .status(CustomException.ALREADY_EXIST_EMAIL.getStatusCode())
            .body(CustomException.ALREADY_EXIST_EMAIL.toDto());
    }

    @ExceptionHandler(AlreadyExistUsernameException.class)
    public ResponseEntity<ExceptionResponseDto> handleAlreadyExistUsernameException() {
        return ResponseEntity
            .status(CustomException.ALREADY_EXIST_USERNAME.getStatusCode())
            .body(CustomException.ALREADY_EXIST_USERNAME.toDto());
    }

    @ExceptionHandler(AlreadyExistNicknameException.class)
    public ResponseEntity<ExceptionResponseDto> handleAlreadyExistNicknameException() {
        return ResponseEntity
            .status(CustomException.ALREADY_EXIST_NICKNAME.getStatusCode())
            .body(CustomException.ALREADY_EXIST_NICKNAME.toDto());
    }

    @ExceptionHandler(FailDeleteImageException.class)
    public ResponseEntity<ExceptionResponseDto> handleFailDeleteImageException() {
        return ResponseEntity
            .status(CustomException.FAIL_DELETE_IMAGE.getStatusCode())
            .body(CustomException.FAIL_DELETE_IMAGE.toDto());
    }

    @ExceptionHandler(FailUploadImageException.class)
    public ResponseEntity<ExceptionResponseDto> handleFailUploadImageException() {
        return ResponseEntity
            .status(CustomException.FAIL_UPLOAD_IMAGE.getStatusCode())
            .body(CustomException.FAIL_UPLOAD_IMAGE.toDto());
    }

    @ExceptionHandler(NoImageFileException.class)
    public ResponseEntity<ExceptionResponseDto> handleNoImageFileException() {
        return ResponseEntity
            .status(CustomException.NO_IMAGE_FILE.getStatusCode())
            .body(CustomException.NO_IMAGE_FILE.toDto());
    }

    @ExceptionHandler(NoImageTypeException.class)
    public ResponseEntity<ExceptionResponseDto> handleNoImageTypeException() {
        return ResponseEntity
            .status(CustomException.NO_IMAGE_TYPE.getStatusCode())
            .body(CustomException.NO_IMAGE_TYPE.toDto());
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFoundUserException() {
        return ResponseEntity
            .status(CustomException.NOT_FOUND_USER.getStatusCode())
            .body(CustomException.NOT_FOUND_USER.toDto());
    }

    @ExceptionHandler(NotMatchCheckPassword.class)
    public ResponseEntity<ExceptionResponseDto> handleNotMatchCheckPassword() {
        return ResponseEntity
            .status(CustomException.NOT_MATCH_CHECK_PASSWORD.getStatusCode())
            .body(CustomException.NOT_MATCH_CHECK_PASSWORD.toDto());
    }

    @ExceptionHandler(NotMatchConfirmationPhrase.class)
    public ResponseEntity<ExceptionResponseDto> handleNotMatchConfirmationPhrase() {
        return ResponseEntity
            .status(CustomException.NOT_MATCH_CONFIRMATION_PHRASE.getStatusCode())
            .body(CustomException.NOT_MATCH_CONFIRMATION_PHRASE.toDto());
    }

    @ExceptionHandler(NotMatchCurrentPassword.class)
    public ResponseEntity<ExceptionResponseDto> handleNotMatchCurrentPassword() {
        return ResponseEntity
            .status(CustomException.NOT_MATCH_CURRENT_PASSWORD.getStatusCode())
            .body(CustomException.NOT_MATCH_CURRENT_PASSWORD.toDto());
    }
}
