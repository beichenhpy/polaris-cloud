package cn.beichenhpy.enums.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常对应的code和msg
 * <PRE>
 *
 * </PRE>
 * CREATE_TIME: 2022/5/28 17:49
 *
 * @author beichenhpy
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
public enum BizExceptionEnum {

    COMMON_EXCEPTION(BizExceptionCodeConstant.COMMON_EXCEPTION_CODE, "系统异常");


    private final String code;
    private final String msg;
}
