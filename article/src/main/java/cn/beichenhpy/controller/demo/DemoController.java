package cn.beichenhpy.controller.demo;

import cn.beichenhpy.service.feign.demo.FileFeignService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
public class DemoController {
    private final FileFeignService fileFeignService;
    /**
     * 配置异常数 fallback生效
     * 注意：fallback的方法参数必须与修饰的相同，可以添加异常类
     * @param name 参数
     * @return 返回值
     */
    @GetMapping("/test")
    @SentinelResource(value = "test",fallback = "fallback")
    public ResponseEntity<String> test(String name) {
        if ("error".endsWith(name)){
            throw new RuntimeException("降级");
        }
        return fileFeignService.test(name);
    }

    /**
     * 异常埋点的fallback方法
     * @param name 与原方法要相同
     * @param throwable 异常
     * @return 返回值
     */
    public ResponseEntity<String> fallback(String name,Throwable throwable) {
        return ResponseEntity.badRequest().body("埋点"+throwable.getMessage());
    }
}
