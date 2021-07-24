package cn.beichenhpy.modal;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote 树及结构 content为具体查询内容
 * @since 2021/7/24 12:57
 */
@Data
public class Tree{
    private Content content;
    private List<Tree> children;
}
