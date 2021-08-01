package cn.beichenhpy.websocket.modal.body;

import cn.beichenhpy.websocket.modal.SendToType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/2 00:00
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class HeartBeatMessage extends BaseMessage{
    public HeartBeatMessage(String from, String content, String to, SendToType type) {
        super(from, content, "server", SendToType.SINGLE);
    }
}
