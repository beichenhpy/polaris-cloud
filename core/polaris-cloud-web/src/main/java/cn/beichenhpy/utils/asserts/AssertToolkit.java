package cn.beichenhpy.utils.asserts;


import cn.beichenhpy.exception.feign.FeignResponseFailException;
import cn.beichenhpy.exception.file.FileNotUploadException;
import cn.beichenhpy.exception.file.FileUploadFailException;
import cn.hutool.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote AssertToolkit description：Assert工具类
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
     * feign请求失败 调用fallback
     *
     * @param serviceName 错误信息
     */
    public static void feignResponseFail(int statusCode, @Nullable String serviceName) {
        String sb = "feign fallback,check feign service is [" +
                Optional.ofNullable(serviceName).orElse("unknown") +
                "]";
        if (statusCode == HttpStatus.HTTP_UNAVAILABLE) {
            throw new FeignResponseFailException(statusCode, sb);
        }
    }
}
