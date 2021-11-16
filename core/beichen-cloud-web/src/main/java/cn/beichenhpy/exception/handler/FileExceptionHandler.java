package cn.beichenhpy.exception.handler;

import cn.beichenhpy.exception.file.FileNotUploadException;
import cn.beichenhpy.exception.file.FileUploadFailException;
import cn.beichenhpy.modal.ErrorMessage;
import cn.beichenhpy.modal.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote FileExceptionHandler description：文件服务异常处理
 * todo 想出一个好的异常处理方案
 * @see FileNotUploadException
 * @see FileUploadFailException
 * @since 2021/5/9 8:18 下午
 */
@Slf4j
@ControllerAdvice
public class FileExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> maxUploadSizeExceededExceptionHandler(HttpServletRequest request, MaxUploadSizeExceededException e) {
        log.error(e.getMessage());
        return R.F(HttpStatus.PAYLOAD_TOO_LARGE, request.getServletPath(), "上传文件过大");
    }

    @ExceptionHandler(FileNotUploadException.class)
    public void fileNotUploadExceptionHandler(FileNotUploadException e, HttpServletResponse response) {
        log.error(e.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(FileUploadFailException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> fileUploadFailExceptionHandler(HttpServletRequest request, FileUploadFailException e) {
        log.error(e.getMessage());
        return R.F_C(request.getServletPath(), "文件上传失败");
    }

    @ExceptionHandler(SizeException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> fileServiceFailResponseExceptionHandler(HttpServletRequest request, SizeException e) {
        log.error(e.getMessage());
        return R.F_C(request.getServletPath(), "上传文件过大");
    }

}
