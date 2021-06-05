package cn.beichenhpy.files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote FilesApplication description：文件服务入口
 * @since 2021/5/8 3:18 下午
 */

@EnableDiscoveryClient
@SpringBootApplication
public class FilesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilesApplication.class, args);
    }

}
