package cn.beichenhpy.websocket.modal.body;

import cn.beichenhpy.websocket.modal.NoticeLevel;
import cn.beichenhpy.websocket.modal.SendToType;
import lombok.*;


/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/1 18:12
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeMessage extends BaseMessage{
    /**
     * 通知等级 LOW/MIDDLE/HIGH
     */
    private NoticeLevel level;

    public NoticeMessage(String from, String content, String to, SendToType type, NoticeLevel level) {
        super(from, content, to, type);
        this.level = level;
    }
}
