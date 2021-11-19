package cn.beichenhpy.modal;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote 业务实体基类
 * @since 2021/7/24 14:37
 */
@Data
public class Content {
    @TableField("id")
    private String id;
    @TableField("parent_id")
    private String parentId;
}
