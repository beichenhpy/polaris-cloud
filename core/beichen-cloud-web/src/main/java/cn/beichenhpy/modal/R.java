package cn.beichenhpy.modal;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class R implements Serializable {

    private static final long serialVersionUID = -7646240998582769873L;

    /**
     * 初始化方法
     *
     * @param status http状态
     * @param body   返回值体
     * @param <T>    泛型
     * @return 返回值
     */
    public static <T> ResponseEntity<T> I(HttpStatus status, T body) {
        return ResponseEntity
                .status(status)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(body);
    }

    /**
     * 成功
     *
     * @param body 参数
     * @param <T>  泛型
     * @return 返回值
     */
    public static <T> ResponseEntity<T> S(T body) {
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(body);
    }

    /**
     * 失败
     *
     * @param status  http状态
     * @param path    请求路径
     * @param message 错误信息
     * @return 返回返回值
     */
    public static ResponseEntity<ErrorMessage> F(HttpStatus status, String path, String message) {
        return I(
                status,
                ErrorMessage.builder()
                        .timestamp(LocalDateTime.now())
                        .path(path)
                        .status(status)
                        .code(status.value())
                        .message(message).build()
        );
    }

    /**
     * 经常用的错误状态 500
     *
     * @param path    请求路径
     * @param message 原因
     * @return 返回值
     */
    public static ResponseEntity<ErrorMessage> F_C(String path, String message) {
        return F(HttpStatus.INTERNAL_SERVER_ERROR, path, message);
    }
}
