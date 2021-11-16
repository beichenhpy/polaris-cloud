package cn.beichenhpy.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote error信息
 * @since 2021/6/28 14:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private Integer code;
    private String message;
    private String path;
}
