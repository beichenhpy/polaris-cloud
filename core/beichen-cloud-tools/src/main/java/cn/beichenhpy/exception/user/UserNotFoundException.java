package cn.beichenhpy.exception.user;

import cn.beichenhpy.exception.NoNeedRollback;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/1 20:11
 */
@Getter
@Setter
@AllArgsConstructor
public class UserNotFoundException extends RuntimeException implements NoNeedRollback {
    private String message;
}
