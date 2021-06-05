package cn.beichenhpy.articles;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote ArticlesApplication description：文章服务入口
 * @since 2021/5/8 2:09 下午
 */
@MapperScan("cn.beichenhpy.articles.mapper.xml")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.beichenhpy.*"})
public class ArticlesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticlesApplication.class, args);
    }

}
