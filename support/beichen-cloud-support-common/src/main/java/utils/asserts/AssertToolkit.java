package utils.asserts;


import cn.hutool.core.lang.Assert;
import exception.file.FileNotUploadException;
import exception.file.FileUploadFailException;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote AssertToolkit description：
 * @since 2021/5/11 3:57 下午
 */
public class AssertToolkit extends Assert {

    public static void fileNotNull(Object file){
        if (file == null){
            throw new FileNotUploadException();
        }
    }

    public static void filenameNotNull(String filename){
        if (filename == null){
            throw new FileUploadFailException();
        }
    }
}
