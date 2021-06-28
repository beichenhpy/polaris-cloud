package cn.beichenhpy.exception.common.advice;

import cn.beichenhpy.exception.modal.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author beichenhpy
 * @apiNote 通用异常处理
 */
@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> illegalArgumentExceptionHandler(HttpServletRequest request, IllegalArgumentException e) {
        e.printStackTrace();
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
