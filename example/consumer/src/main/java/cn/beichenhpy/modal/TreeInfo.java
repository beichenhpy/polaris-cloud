package cn.beichenhpy.modal;

import cn.beichenhpy.util.TreeHelper;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/7/24 13:43
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("tree")
@Data
public class TreeInfo extends TreeHelper.Tree{
    private String name;
}
