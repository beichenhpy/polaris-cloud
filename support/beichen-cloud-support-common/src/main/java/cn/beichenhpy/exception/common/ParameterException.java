package cn.beichenhpy.exception.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author beichenhpy
 * @apiNote 参数异常
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParameterException extends IllegalArgumentException{
    private String message;
}
