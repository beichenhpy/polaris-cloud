package cn.beichenhpy.websocket.modal.body;

import cn.beichenhpy.websocket.modal.NoticeLevel;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/1 18:12
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class NoticeMessage extends BaseMessage{
    /**
     * 通知等级 LOW/MIDDLE/HIGH
     */
    private NoticeLevel level;


    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Accessors(chain = true)
    public static class NoticeShowMessage extends BaseMessage.ShowMessage {
        private NoticeLevel level;
    }
}
