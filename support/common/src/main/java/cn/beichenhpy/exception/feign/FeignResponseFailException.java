package cn.beichenhpy.exception.feign;

import cn.beichenhpy.exception.NeedRollback;
import lombok.Getter;
import lombok.Setter;
/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/6/28 12:11
 */
@Getter
@Setter
public class FeignResponseFailException extends RuntimeException implements NeedRollback {
    private String message;
    private int statusCode;

    public FeignResponseFailException(int statusCode,String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
