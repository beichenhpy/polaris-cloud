package cn.beichenhpy.utils;

import cn.beichenhpy.Exception.FileNotUploadException;
import cn.beichenhpy.Exception.FileUploadFailException;
import org.springframework.util.Assert;

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
