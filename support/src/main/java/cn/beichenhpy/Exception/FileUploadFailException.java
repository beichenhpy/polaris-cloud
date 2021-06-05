package cn.beichenhpy.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote FileUploadFailException description：文件上传失败异常
 * <br>针对于{@code files-service}服务的异常类 具体handler见具体服务
 * @see java.lang.RuntimeException
 * @since 2021/5/9 8:38 下午
 */
@Getter
@Setter
@AllArgsConstructor
public class FileUploadFailException extends RuntimeException{
    private String message;
    public FileUploadFailException(){
        this.message  = "file upload fail";
    }
}
