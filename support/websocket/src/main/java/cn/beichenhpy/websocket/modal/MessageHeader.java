package cn.beichenhpy.websocket.modal;

import lombok.Getter;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote 消息类型
 * @since 2021/8/1 18:09
 */
@Getter
public enum MessageHeader {
    CHAT,
    NOTICE,
    HEARTBEAT
}
