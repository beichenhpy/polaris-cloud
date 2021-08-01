package cn.beichenhpy.websocket.modal.body;

import cn.beichenhpy.websocket.modal.SendToType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/1 18:14
 */
@Data
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


    /**
     * 展示给前端的信息
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ShowMessage{
        /**
         * 发送者
         */
        private String from;
        /**
         * 内容
         */
        private String content;
        /**
         * 时间戳
         */
        private LocalDateTime timestamp;
    }
}
