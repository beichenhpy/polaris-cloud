package cn.beichenhpy.exception.handler;

import cn.beichenhpy.modal.ErrorMessage;
import cn.beichenhpy.modal.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author beichenhpy
 * @apiNote 通用异常处理
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> illegalArgumentExceptionHandler(HttpServletRequest request, IllegalArgumentException e) {
        log.error(e.getMessage());
        return R.F_C(request.getServletPath(), e.getMessage());
    }
}
