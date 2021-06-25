package cn.beichenhpy.files.controller;

import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote FeignTestController description：
 * @since 2021/6/25 20:45
 */
@RestController
public class FeignTestController {
    @GetMapping("/test")
    @SneakyThrows
    public ResponseEntity<String> test(@RequestParam(value = "name")String name){
        Thread.sleep(15000);
        return ResponseEntity.ok(name);
    }
}
