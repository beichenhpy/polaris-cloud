package cn.beichenhpy.modal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/7/24 14:37
 */
@Data
public class Content {
    @TableField("id")
    private String id;
    @TableField("parent_id")
    private String parentId;
}
