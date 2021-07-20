package cn.beichenhpy.controller;

import cn.beichenhpy.modal.Article;
import cn.beichenhpy.modal.Comment;
import cn.beichenhpy.service.ConsumerService;
import cn.beichenhpy.service.feign.ProviderFeignService;
import cn.beichenhpy.utils.asserts.AssertToolkit;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ConsumerController {

    private final ProviderFeignService providerFeignService;
    private final ConsumerService consumerService;
    /**
     * 配置异常数 fallback生效
     * 注意：fallback的方法参数必须与修饰的相同，可以添加异常类
     * @param aid 参数
     * @return 返回值
     */
    @GetMapping("/info/{aid}")
    public ResponseEntity<Article> info(@PathVariable("aid") String aid) {
        ResponseEntity<List<Comment>> response = providerFeignService.getCommentInfoByArticleId(aid);
        AssertToolkit.feignResponseFail(response.getStatusCode().value(),"provider");
        Article article = consumerService.getById(aid);
        article.setComments(response.getBody());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(article);
    }

    /**
     * 证明，事务回滚和ControllerAdvice全局异常管理不冲突，因为事务回滚还会抛出原异常
     */
    @GetMapping("/testControllerAdviceAndTransactional")
    @Transactional
    public void testControllerAdviceAndTransactional(){
        LambdaUpdateWrapper<Article> update = new LambdaUpdateWrapper<Article>().eq(Article::getId, "1408375341166145537").set(Article::getTitle, "update");
        consumerService.update(update);
        throw new IllegalArgumentException("事务测试");
    }
}
