package cn.beichenhpy.service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cn.beichenhpy.modal.Comment;
import cn.beichenhpy.service.feign.fallback.ProviderFeignServiceImpl;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/6/28 12:04
 */
@FeignClient(name = "provider",fallback = ProviderFeignServiceImpl.class)
public interface ProviderFeignService {
    @GetMapping("/api/v1/info/{aid}")
    ResponseEntity<List<Comment>> getCommentInfoByArticleId(@PathVariable("aid") String aid);
}
