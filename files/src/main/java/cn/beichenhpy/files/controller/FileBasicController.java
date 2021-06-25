package cn.beichenhpy.files.controller;

import reactor.util.annotation.Nullable;
import utils.AssertToolkit;

import javax.servlet.http.HttpServletResponse;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote FileBasicController description：文件服务基类
 * @since 2021/5/11 4:57 下午
 */
public class FileBasicController {

    /**
     * 设置为强制下载并且指定文件名
     * @param response response响应
     * @param filename 文件名
     */
    protected void setForceDownload(HttpServletResponse response, String filename){
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + filename);
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
