package cn.beichenhpy;

import cn.beichenhpy.exception.feign.FeignResponseFailException;
import cn.beichenhpy.modal.ErrorMessage;
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
 * @apiNote feign异常处理
 * @see FeignResponseFailException 参数异常
 */
@RestControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler(FeignResponseFailException.class)
    public ResponseEntity<ErrorMessage> FeignResponseFailExceptionHandler(HttpServletRequest request, FeignResponseFailException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(
                        ErrorMessage.builder()
                                .message(e.getMessage())
                                .code(HttpStatus.SERVICE_UNAVAILABLE.value())
                                .path(request.getServletPath())
                                .timestamp(LocalDateTime.now())
                                .status(HttpStatus.SERVICE_UNAVAILABLE).build()
                );
    }
}
