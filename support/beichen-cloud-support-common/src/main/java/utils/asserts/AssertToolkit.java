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

    /**
     * 文件未上传
     * @param file 文件
     */
    public static void fileNotNull(Object file){
        if (file == null){
            throw new FileNotUploadException();
        }
    }

    /**
     * 文件山川失败
     * @param filename 文件名
     */
    public static void filenameNotNull(String filename){
        if (filename == null){
            throw new FileUploadFailException();
        }
    }
}
