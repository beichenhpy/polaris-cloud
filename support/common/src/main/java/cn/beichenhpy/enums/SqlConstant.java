package cn.beichenhpy.enums;

import lombok.Getter;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/7/24 14:59
 */
@Getter
public enum SqlConstant {
    PARENT_ID("parent_id","树形父级id");

    private final String value;
    private final String name;

    SqlConstant(String value, String name) {
        this.value = value;
        this.name = name;
    }
}
