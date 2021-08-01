package cn.beichenhpy.websocket.modal.show;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/1 23:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowMessage {
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