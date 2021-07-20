package cn.beichenhpy.util;

import cn.beichenhpy.utils.asserts.AssertToolkit;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.lang.Nullable;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote GridFs工具类
 * @since 2021/7/20 21:09
 */
public class GridFsUtil {

    @Resource
    private GridFsTemplate gridFsTemplate;
    @Resource
    private GridFSBucket bucket;

    /**
     * 存储文件到GridFs中
     * @param inputStream 输入流
     * @param originName 文件原始名
     * @param contentType 文件类型
     * @return 返回ObjectId
     */
    public String storeFile(InputStream inputStream, String originName, String contentType) {
        ObjectId id = gridFsTemplate.store(inputStream, getFilename(originName), contentType);
        return id.toHexString();
    }

    /**
     * 下载文件
     * @param objectId 文件ObjectId
     * @param outputStream 输出流
     * @return 文件是否存在
     */
    public boolean getFile(String objectId, OutputStream outputStream){
        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(objectId)));
        if (file == null){
            return false;
        }
        bucket.downloadToStream(file.getObjectId(),outputStream);
        return true;
    }

    /**
     * 返回文件名 文件名 + 时间戳 + 后缀
     *
     * @param originName 原文件名
     * @return 返回生成后的 文件名 + 时间戳 + 后缀
     */
    protected String getFilename(@Nullable String originName) {
        AssertToolkit.filenameNotNull(originName);
        String filename = originName.substring(0, originName.lastIndexOf("."));
        String contentType = originName.substring(originName.lastIndexOf("."));
        return filename + "_" + System.currentTimeMillis() + contentType;
    }
}
