package cn.beichenhpy.exception.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/6/28 12:11
 */
@Getter
@Setter
public class FeignResponseFailException extends ResponseStatusException {
    private String message;

    public FeignResponseFailException(HttpStatus status) {
        super(status);
    }

    public FeignResponseFailException(HttpStatus status, String message) {
        super(status, message);
    }
}
