package cn.beichenhpy.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
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
@SuperBuilder
public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = -2033191967513013366L;
    private LocalDateTime timestamp;
    private HttpStatus status;
    private Integer code;
    private String message;
    private String path;
}
