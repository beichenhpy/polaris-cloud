package cn.beichenhpy.enums;

import lombok.Getter;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/7/23 22:54
 */
@Getter
public enum StatusEnum {
    /**
     * 启用
     */
    ENABLE("enable", "启用"),

    /**
     * 禁用
     */
    DISABLE("disable", "禁用");

    private final String code;

    private final String message;

    StatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据code获取枚举
     */
    public static StatusEnum codeToEnum(String code) {
        if (null != code) {
            for (StatusEnum e : StatusEnum.values()) {
                if (e.getCode().equals(code)) {
                    return e;
                }
            }
        }
        return null;
    }
}
