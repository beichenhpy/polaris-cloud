package cn.beichenhpy.modal;

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
public class TreeInfo extends Content{
    private String name;
}
