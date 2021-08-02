package cn.beichenhpy.modal;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author beichenhpy
 * @version 0.0.1
 * @apiNote Article description：文章实体类
 * @since 2021/6/25 17:50
 */
@TableName("comment")
@Data
public class Comment {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String content;

    private String postUserName;

    private String image;

    private String articleId;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
