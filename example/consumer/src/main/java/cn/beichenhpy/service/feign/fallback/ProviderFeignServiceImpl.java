package cn.beichenhpy.service.feign.fallback;

import cn.beichenhpy.modal.Comment;
import cn.beichenhpy.service.feign.ProviderFeignService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/6/28 12:05
 */
@Component
public class ProviderFeignServiceImpl implements ProviderFeignService {
    @Override
    public ResponseEntity<List<Comment>> getCommentInfoByArticleId(String aid) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
