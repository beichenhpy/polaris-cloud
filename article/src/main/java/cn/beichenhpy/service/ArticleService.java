package cn.beichenhpy.service;

import cn.beichenhpy.modal.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote ArticleService description：文章业务抽象接口
 * @since 2021/6/25 17:56
 */
public interface ArticleService extends IService<Article> {
    Boolean addArticle(Article article);
}
