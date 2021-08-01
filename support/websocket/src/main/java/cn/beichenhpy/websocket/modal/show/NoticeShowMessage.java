package cn.beichenhpy.websocket.modal.show;

import cn.beichenhpy.websocket.modal.NoticeLevel;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/1 23:09
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeShowMessage extends ShowMessage {
    private NoticeLevel level;

    public NoticeShowMessage(String from, String content, LocalDateTime timestamp, NoticeLevel level) {
        super(from, content, timestamp);
        this.level = level;
    }
}