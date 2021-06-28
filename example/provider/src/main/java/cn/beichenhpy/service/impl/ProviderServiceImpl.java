package cn.beichenhpy.service.impl;

import cn.beichenhpy.mapper.ProviderMapper;
import cn.beichenhpy.modal.Comment;
import cn.beichenhpy.service.ProviderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/6/28 11:42
 */
@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Comment> implements ProviderService {
}
