package cn.beichenhpy.utils.file;

import cn.beichenhpy.exception.file.FileNotUploadException;
import cn.beichenhpy.utils.asserts.AssertToolkit;
import org.springframework.lang.Nullable;

import java.io.*;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/7/20 22:04
 */
public class FileUtil {
    /**
     * 下载文件
     * @param filename 文件名
     * @param uploadPath 存储路径
     * @param outputStream 输出流
     */
    public static void downloadFile(String filename,String uploadPath,OutputStream outputStream){
        if (!(filename == null || filename.length() == 0)) {
            File file = new File(uploadPath + File.separator + filename);
            if (!file.exists()) {
                throw new FileNotUploadException();
            }
            try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                try {
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buff)) > 0) {
                        outputStream.write(buff, 0, len);
                    }
                } catch (IOException e) {
                    throw new FileNotUploadException();
                }
            } catch (IOException e) {
                throw new FileNotUploadException();
            }
        }
    }

    /**
     * 返回文件名 文件名 + 时间戳 + 后缀
     * @param originName 原文件名
     * @return 返回生成后的 文件名 + 时间戳 + 后缀
     */
    protected String getFilename(@Nullable String originName){
        AssertToolkit.filenameNotNull(originName);
        String filename = originName.substring(0,originName.lastIndexOf("."));
        String contentType = originName.substring(originName.lastIndexOf("."));
        return filename + "_"  + System.currentTimeMillis() + contentType;
    }
}
