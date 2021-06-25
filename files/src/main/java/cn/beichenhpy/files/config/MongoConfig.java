package cn.beichenhpy.files.config;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;

import javax.annotation.Resource;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote MongoConfig description：创建GridFSBucket
 * @since 2021/5/11 4:28 下午
 */
@Configuration
public class MongoConfig {
    @Resource
    private MongoDatabaseFactory mongoDatabaseFactory;
    @Bean(name = "bucket")
    public GridFSBucket bucket(){
        return GridFSBuckets.create(mongoDatabaseFactory.getMongoDatabase());
    }
}
