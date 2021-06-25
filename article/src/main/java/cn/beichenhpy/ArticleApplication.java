package cn.beichenhpy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote ArticleApplication description：
 * @since 2021/6/25 17:26
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
    }
}
