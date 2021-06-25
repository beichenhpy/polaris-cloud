package cn.beichenhpy.files.controller;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSFile;
import exception.file.FileNotUploadException;
import exception.file.FileUploadFailException;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote GridFsController description：利用MongoDB的GridFs存储文件
 * @since 2021/5/11 2:01 下午
 */
@RestController
@RequestMapping("/static/GFS")
public class GridFsController extends FileBasicController{

    @Resource
    private GridFsTemplate gridFsTemplate;
    @Resource
    private GridFSBucket bucket;

    @PostMapping("/upload")
    public ResponseEntity<List<String>> upload(@RequestParam("files") MultipartFile[] files) {
        List<String> filePath = new ArrayList<>();
        for (MultipartFile file : files) {
            try (InputStream inputStream = file.getInputStream()) {
                ObjectId fileId = gridFsTemplate.store(inputStream, getFilename(file.getOriginalFilename()), file.getContentType());
                filePath.add(fileId.toHexString());
            } catch (IOException e) {
                throw new FileUploadFailException();
            }
        }
        return ResponseEntity.ok().body(filePath);
    }

    @GetMapping("/view/{objectId}")
    public void view(HttpServletResponse response, @PathVariable("objectId") String objectId) {
        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(objectId)));
        if (file == null) {
            response.setStatus(404);
            throw new FileNotUploadException();
        }
        try (OutputStream outputStream = response.getOutputStream()) {
            bucket.downloadToStream(file.getObjectId(),outputStream);
        } catch (IOException e) {
            throw new FileNotUploadException();
        }
    }


}
