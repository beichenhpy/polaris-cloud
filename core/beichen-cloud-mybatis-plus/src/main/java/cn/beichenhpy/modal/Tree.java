package cn.beichenhpy.modal;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote 树及结构 子类可进行扩充
 * @since 2021/7/24 12:57
 */
@Data
public class Tree{
    private String id;
    private String parentId;
    /**
     * 叶子节点，（下一级）
     */
    @TableField(exist = false)
    private List<? extends Tree> children;
}
