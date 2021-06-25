package cn.beichenhpy.service.impl;

import cn.beichenhpy.mapper.ArticleMapper;
import cn.beichenhpy.modal.Article;
import cn.beichenhpy.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote ArticleServiceImpl description：文章业务实现层
 * @since 2021/6/25 17:57
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService{
}
