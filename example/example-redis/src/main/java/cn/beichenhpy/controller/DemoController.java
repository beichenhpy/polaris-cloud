package cn.beichenhpy.controller;

import cn.beichenhpy.modal.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote 入口
 * @since 2021/8/12 21:33
 */
@Slf4j
@RestController
public class DemoController {

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    @PostMapping("/set")
    public void set() {
        User xiaoMing = new User().setName("xiaoMing").setGender(true);
        User xiaoXin = new User().setName("xiaoXin").setGender(false);
        List<User> users = new ArrayList<>();
        users.add(xiaoMing);
        users.add(xiaoXin);
        redisTemplate.opsForList().rightPushAll("USERS", users);
        redisTemplate.expire("USERS",1, TimeUnit.MINUTES);
    }

    @GetMapping("/get/{key}")
    public ResponseEntity<List<User>> get(@PathVariable(value = "key") String key) {
        List<User> users = redisTemplate.opsForList().range(key, 0, -1);
        return ResponseEntity.ok(users);
    }
}
