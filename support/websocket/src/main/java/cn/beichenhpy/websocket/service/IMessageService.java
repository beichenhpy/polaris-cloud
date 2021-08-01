package cn.beichenhpy.websocket.service;

import cn.beichenhpy.websocket.WebSocketServer;
import cn.beichenhpy.websocket.modal.body.BaseMessage;
import cn.beichenhpy.websocket.modal.show.ShowMessage;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote 消息服务层抽象
 * @since 2021/8/1 18:24
 */
public interface IMessageService<T extends BaseMessage> {
    /**
     * 抽象出发送消息方法
     *
     * @param message 抽象基类的子类
     */
    void sendMessage(T message, WebSocketServer webSocketServer) throws IOException;

    /**
     * 组装展示消息
     *
     * @param message 消息
     * @return 返回展示的消息
     */
    default String assemble(T message) {
        return JSON.toJSONString(
                new ShowMessage(
                        message.getFrom(),
                        message.getContent(),
                        LocalDateTime.now()
                )
        );
    }
}
