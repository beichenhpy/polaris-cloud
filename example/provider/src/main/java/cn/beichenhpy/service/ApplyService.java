package cn.beichenhpy.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.beichenhpy.modal.Apply;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/2 20:00
 */
public interface ApplyService extends IService<Apply> {
    void importData();
}
