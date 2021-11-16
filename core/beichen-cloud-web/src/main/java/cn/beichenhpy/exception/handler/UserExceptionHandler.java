package cn.beichenhpy.exception.handler;

import cn.beichenhpy.exception.user.UserNotFoundException;
import cn.beichenhpy.modal.ErrorMessage;
import cn.beichenhpy.modal.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

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
        return R.F_C(request.getServletPath(), e.getMessage());
    }
}
