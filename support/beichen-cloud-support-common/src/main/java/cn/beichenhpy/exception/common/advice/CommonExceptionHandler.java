package cn.beichenhpy.exception.common.advice;

import cn.beichenhpy.exception.common.ParameterException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author beichenhpy
 * @apiNote 通用异常处理
 * @see ParameterException 参数异常
 */
@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(ParameterException.class)
    public ResponseEntity<String> parameterExceptionHandler(ParameterException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
