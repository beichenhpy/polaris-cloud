package cn.beichenhpy.exception.handler;

import cn.beichenhpy.exception.feign.FeignResponseFailException;
import cn.beichenhpy.modal.ErrorMessage;
import cn.beichenhpy.modal.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author beichenhpy
 * @apiNote feign异常处理
 * @see FeignResponseFailException 参数异常
 */
@Slf4j
@RestControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler(FeignResponseFailException.class)
    public ResponseEntity<ErrorMessage> FeignResponseFailExceptionHandler(HttpServletRequest request, FeignResponseFailException e) {
        log.error(e.getMessage());
        return R.F(HttpStatus.SERVICE_UNAVAILABLE, request.getServletPath(), e.getMessage());
    }
}
