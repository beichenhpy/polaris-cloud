package cn.beichenhpy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import cn.beichenhpy.mapper.ConsumerMapper;
import cn.beichenhpy.modal.Article;
import cn.beichenhpy.service.ConsumerService;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/6/28 11:42
 */
@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Article> implements ConsumerService {
}
