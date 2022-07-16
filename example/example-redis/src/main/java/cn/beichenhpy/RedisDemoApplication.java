package cn.beichenhpy;

import cn.beichenhpy.annotation.EnablePolarisWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote start
 * @since 2021/8/12 21:40
 */
@EnablePolarisWeb
@SpringBootApplication
public class RedisDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisDemoApplication.class,args);
    }
}
