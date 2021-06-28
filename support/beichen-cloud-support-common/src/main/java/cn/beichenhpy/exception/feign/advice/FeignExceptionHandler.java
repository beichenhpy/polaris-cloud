package cn.beichenhpy.exception.feign.advice;

import cn.beichenhpy.exception.feign.FeignResponseFailException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author beichenhpy
 * @apiNote feign异常处理
 * @see FeignResponseFailException 参数异常
 */
@RestControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler(FeignResponseFailException.class)
    public ResponseEntity<String> FeignResponseFailExceptionHandler(FeignResponseFailException e) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(e.getMessage());
    }
}
