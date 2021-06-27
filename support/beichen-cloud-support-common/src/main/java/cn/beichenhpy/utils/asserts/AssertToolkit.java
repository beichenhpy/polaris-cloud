package cn.beichenhpy.utils.asserts;


import cn.hutool.core.lang.Assert;
import cn.beichenhpy.exception.common.ParameterException;
import cn.beichenhpy.exception.file.FileNotUploadException;
import cn.beichenhpy.exception.file.FileUploadFailException;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote AssertToolkit description：
 * @since 2021/5/11 3:57 下午
 */
public class AssertToolkit extends Assert {

    /**
     * 文件未上传
     *
     * @param file 文件
     */
    public static void fileNotNull(Object file) {
        if (file == null) {
            throw new FileNotUploadException();
        }
    }

    /**
     * 文件山川失败
     *
     * @param filename 文件名
     */
    public static void filenameNotNull(String filename) {
        if (filename == null) {
            throw new FileUploadFailException();
        }
    }

    /**
     * 参数不为空
     * @param parameter 参数
     * @param message 异常信息
     */
    public static void parameterNotNull(Object parameter, String message) {
        if (parameter == null) {
            throw new ParameterException(message);
        }
    }
}
