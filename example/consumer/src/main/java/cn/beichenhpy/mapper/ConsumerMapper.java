package cn.beichenhpy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import cn.beichenhpy.modal.Article;

/**
 * @author beichenhpy
 * @apiNote
 * @version 1.0.0
 * @since 2021/6/28 11:40
*/
@Mapper
public interface ConsumerMapper extends BaseMapper<Article> {
}
