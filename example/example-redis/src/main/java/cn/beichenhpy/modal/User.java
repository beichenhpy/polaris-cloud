package cn.beichenhpy.modal;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote modal
 * @since 2021/8/12 21:34
 */
@Data
@Accessors(chain = true)
public class User {
    private String name;
    private boolean gender;
}
