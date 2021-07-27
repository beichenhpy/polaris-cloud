package cn.beichenhpy.enums;

import lombok.Getter;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/7/23 22:53
 * @see <a href="https://github.com/matevip/matecloud/tree/dev/mate-core/mate-starter-validator">mate-starter-validator</a>
 */
@Getter
public enum IsOkEnum {
    /**
     * 是
     */
    Y("Y","0", "是"),

    /**
     * 否
     */
    N("N", "-1","否");

    private final String code;
    private final String num;
    private final String message;

    IsOkEnum(String code,String num, String message) {
        this.code = code;
        this.num = num;
        this.message = message;
    }
}
