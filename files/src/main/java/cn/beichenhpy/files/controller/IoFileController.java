package cn.beichenhpy.files.controller;

import exception.file.FileNotUploadException;
import exception.file.FileUploadFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote IoFileController description：文件服务对外显示连接-使用本地存储
 * @since 2021/5/9 8:03 下午
 */
@Slf4j
@RestController
@RequestMapping("/static/local")
public class IoFileController extends FileBasicController{

    @Value("${file.path}")
    private String uploadPath;

    @GetMapping("/view/{path}")
    public void view(HttpServletResponse response, @PathVariable(name = "path") String path) {
        if (!(path == null || path.length() == 0)) {
            File file = new File(this.uploadPath + File.separator + path);
            if (!file.exists()) {
                response.setStatus(404);
                throw new FileNotUploadException();
            }
            try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                try (OutputStream outputStream = response.getOutputStream()) {
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buff)) > 0) {
                        outputStream.write(buff, 0, len);
                    }
                } catch (IOException e) {
                    throw new FileNotUploadException();
                }
                response.flushBuffer();
            } catch (IOException e) {
                response.setStatus(404);
                throw new FileNotUploadException();
            }
        }
    }

    @PostMapping("upload")
    public ResponseEntity<List<String>> upload(@RequestParam MultipartFile[] files) {
        List<String> filePaths = new ArrayList<>(files.length);
        for (MultipartFile file : files) {
            String filename = getFilename(file.getOriginalFilename());
            String path = this.uploadPath + File.separator + filename;
            try {
                file.transferTo(new File(path));
                filePaths.add(filename);
            } catch (IOException e) {
                throw new FileUploadFailException();
            }
        }
        return ResponseEntity.ok().body(filePaths);
    }


}
