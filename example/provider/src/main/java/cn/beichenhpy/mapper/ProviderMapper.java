package cn.beichenhpy.mapper;

import cn.beichenhpy.modal.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author beichenhpy
 * @apiNote
 * @version 1.0.0
 * @since 2021/6/28 11:40
*/
@Mapper
public interface ProviderMapper extends BaseMapper<Comment> {
}
