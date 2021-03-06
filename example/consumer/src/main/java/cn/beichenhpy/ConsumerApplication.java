package cn.beichenhpy;

import cn.beichenhpy.annotation.EnablePolarisWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/6/28 11:37
 */
@EnablePolarisWeb
@EnableFeignClients
@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
