package cn.beichenhpy.mapper;

import cn.beichenhpy.modal.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote ArticleMapper description：
 * @since 2021/6/25 17:56
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
