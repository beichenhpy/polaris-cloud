package cn.beichenhpy.websocket.modal;

import lombok.Data;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote 消息 分为消息头和消息体
 * @since 2021/8/1 18:10
 */
@Data
public class Message {
    /**
     * 消息头，分为 CHAT/NOTICE/HEARTBEAT
     */
    private MessageHeader header;
    /**
     * 消息体，JSON类型，根据消息头来转换
     */
    private String body;
}
