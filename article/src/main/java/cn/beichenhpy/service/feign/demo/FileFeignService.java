package cn.beichenhpy.service.feign.demo;

import cn.beichenhpy.service.feign.demo.fallback.FileFeignServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote FileFeignService description：
 * @since 2021/6/25 20:47
 */
@FeignClient(name = "files", contextId = "file-test",fallback = FileFeignServiceFallbackImpl.class)
public interface FileFeignService {
    /**
     * 测试Feign
     * @param name test
     * @return test
     */
    @GetMapping("/test")
    ResponseEntity<String> test(@RequestParam(value = "name") String name);
}
