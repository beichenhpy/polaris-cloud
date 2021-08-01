package cn.beichenhpy.websocket.modal.body;

import cn.beichenhpy.websocket.modal.SendToType;
import lombok.*;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote 聊天消息类
 * @since 2021/8/1 09:44
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ChatMessage extends BaseMessage{
    public ChatMessage(String from, String content, String to, SendToType type) {
        super(from, content, to, type);
    }
    //according biz to add more
}
