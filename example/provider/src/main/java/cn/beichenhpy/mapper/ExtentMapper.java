package cn.beichenhpy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/2 23:00
 */
public interface ExtentMapper<T> extends BaseMapper<T> {
    int insertBatchSomeColumn(List<T> entityList);
    int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) T entity);
}
