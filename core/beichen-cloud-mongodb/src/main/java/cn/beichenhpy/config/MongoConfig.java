package cn.beichenhpy.config;

import cn.beichenhpy.util.GridFsUtil;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.MongoDatabaseFactory;

import javax.annotation.Resource;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote MongoConfig description：创建GridFSBucket
 * @since 2021/5/11 4:28 下午
 */
@Configuration
@Import(GridFsUtil.class)
public class MongoConfig {
    @Resource
    private MongoDatabaseFactory mongoDatabaseFactory;
    @Bean(name = "bucket")
    public GridFSBucket bucket(){
        return GridFSBuckets.create(mongoDatabaseFactory.getMongoDatabase());
    }
}
