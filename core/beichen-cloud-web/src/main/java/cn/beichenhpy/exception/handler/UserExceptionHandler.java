package cn.beichenhpy.exception.handler;

import cn.beichenhpy.exception.modal.ErrorMessage;
import cn.beichenhpy.exception.user.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/1 20:13
 */
@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFoundExceptionHandler(HttpServletRequest request, UserNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(
                        ErrorMessage.builder()
                                .timestamp(LocalDateTime.now())
                                .path(request.getServletPath())
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message(e.getMessage()).build()
                );
    }
}
