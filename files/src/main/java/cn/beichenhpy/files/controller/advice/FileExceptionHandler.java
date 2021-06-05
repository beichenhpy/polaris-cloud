package cn.beichenhpy.files.controller.advice;

import cn.beichenhpy.Exception.FileNotUploadException;
import cn.beichenhpy.Exception.FileUploadFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote FileExceptionHandler description：文件服务异常处理
 * todo 想出一个好的异常处理方案
 * @since 2021/5/9 8:18 下午
 */
@Slf4j
@ControllerAdvice
public class FileExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResponseEntity<String> maxUploadSizeExceededExceptionHandler(MaxUploadSizeExceededException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.PAYLOAD_TOO_LARGE)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body("your file size > 10MB");
    }

    @ExceptionHandler(FileNotUploadException.class)
    public void fileNotUploadExceptionHandler(FileNotUploadException e) {
        log.error(e.getMessage());
    }

    @ExceptionHandler(FileUploadFailException.class)
    @ResponseBody
    public ResponseEntity<String> fileUploadFailExceptionHandler(FileUploadFailException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(e.getMessage());
    }

}
