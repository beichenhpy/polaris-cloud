package cn.beichenhpy.websocket.modal.body;

import cn.beichenhpy.websocket.modal.SendToType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/1 18:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseMessage {

    /**
     * 发送者
     */
    private String from;
    /**
     * 内容
     */
    private String content;

    /**
     * 接受者 当SendToType为GROUP时，为groupId
     */
    private String to;

    /**
     * 发送给谁 ALL/SINGLE/GROUP
     */
    private SendToType type;

}
