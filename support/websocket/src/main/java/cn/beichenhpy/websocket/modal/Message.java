package cn.beichenhpy.websocket.modal;

import lombok.Data;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote 消息类
 * @since 2021/8/1 09:44
 */
@Data
public class Message {
    private String from;
    private String to;
    private MessageType type;
    private String text;
}
