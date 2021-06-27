package cn.beichenhpy.controller;

import cn.beichenhpy.modal.Article;
import cn.beichenhpy.service.ArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote ArticleController description：文章控制层-web入口
 * @since 2021/6/25 18:00
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/article")
    public ResponseEntity<String> addArticle(Article article) {
        boolean saved = articleService.addArticle(article);
        return saved ? ResponseEntity.ok().body("ok") : ResponseEntity.badRequest().build();
    }

    @GetMapping("/articles")
    public ResponseEntity<IPage<Article>> showArticles(@RequestParam(value = "page") int page,
                                                       @RequestParam("size") int size) {
        IPage<Article> articles = articleService.page(new Page<>(page, size));
        return ResponseEntity.ok(articles);
    }
}
