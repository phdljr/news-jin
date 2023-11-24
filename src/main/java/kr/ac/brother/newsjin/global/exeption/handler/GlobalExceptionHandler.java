package kr.ac.brother.newsjin.global.exeption.handler;

import kr.ac.brother.newsjin.global.exeption.dto.ExceptionResponseDto;
import kr.ac.brother.newsjin.global.exeption.type.CustomException;
import kr.ac.brother.newsjin.user.exception.AlreadyExistEmailException;
import kr.ac.brother.newsjin.user.exception.AlreadyExistUsernameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistEmailException.class)
    public ResponseEntity<ExceptionResponseDto> handleAlreadyExistEmailException(){
        return ResponseEntity
            .status(CustomException.ALREADY_EXIST_EMAIL.getStatusCode())
            .body(CustomException.ALREADY_EXIST_EMAIL.toDto());
    }

    @ExceptionHandler(AlreadyExistUsernameException.class)
    public ResponseEntity<ExceptionResponseDto> handleAlreadyExistUsernameException(){
        return ResponseEntity
            .status(CustomException.ALREADY_EXIST_USERNAME.getStatusCode())
            .body(CustomException.ALREADY_EXIST_USERNAME.toDto());
    }
}
