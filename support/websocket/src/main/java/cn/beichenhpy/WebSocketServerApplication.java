package cn.beichenhpy;

import cn.beichenhpy.annotation.EnableBeichenWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/7/31 14:35
 */
@EnableBeichenWeb
@SpringBootApplication
public class WebSocketServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketServerApplication.class, args);
    }
}
