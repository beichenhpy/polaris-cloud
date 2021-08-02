package cn.beichenhpy.modal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/2 19:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Apply {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String vid;
    private String applyType;
    private String remark;
}
