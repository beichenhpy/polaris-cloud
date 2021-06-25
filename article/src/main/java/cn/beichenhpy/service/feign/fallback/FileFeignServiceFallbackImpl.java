package cn.beichenhpy.service.feign.fallback;

import cn.beichenhpy.service.feign.FileFeignService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote FileFeignServiceFallbackImpl description：
 * @since 2021/6/25 21:07
 */
@Component
public class FileFeignServiceFallbackImpl implements FileFeignService {
    @Override
    public ResponseEntity<String> test(String name) {
        return ResponseEntity.badRequest().body("fail");
    }
}
