package cn.beichenhpy.websocket.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote 消息类型
 * @since 2021/8/1 09:45
 */
@AllArgsConstructor
@Getter
public enum MessageType {
    SINGLE(0,"single"),
    GROUP(1,"group"),
    ALL(-1,"all");

    private final Integer id;
    private final String name;

}
