package cn.beichenhpy.websocket.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/1 18:14
 */
@Getter
@AllArgsConstructor
public enum NoticeLevel {
    LOW("0","低等级"),
    MIDDLE("1","中等级"),
    HIGH("2","高等级");
    private final String value;
    private final String name;
}
