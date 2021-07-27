package cn.beichenhpy.exception.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote FileNotUploadException description：文件没有上传
 * <br>针对{@code files-service} 的异常 具体handler 见具体服务
 * @see RuntimeException
 * @since 2021/5/9 8:38 下午
 */
@Getter
@Setter
@AllArgsConstructor
public class FileNotUploadException extends FileRuntimeException {
    private String message;
    public FileNotUploadException(){
        this.message  = "file is not on the disk";
    }
}
