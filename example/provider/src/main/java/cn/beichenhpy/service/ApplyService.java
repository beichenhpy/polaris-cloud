package cn.beichenhpy.service;

import cn.beichenhpy.modal.Apply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/2 20:00
 */
public interface ApplyService extends IService<Apply> {
    void importData();
}
