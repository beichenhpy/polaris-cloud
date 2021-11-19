package cn.beichenhpy.websocket.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;

import com.alibaba.fastjson.JSON;

import cn.beichenhpy.websocket.WebSocketServer;
import cn.beichenhpy.websocket.modal.body.HeartBeatMessage;
import cn.beichenhpy.websocket.modal.show.ShowMessage;
import cn.beichenhpy.websocket.service.IMessageService;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/1 23:59
 */
public class HeartBeatService implements IMessageService<HeartBeatMessage> {
    @Override
    public void sendMessage(HeartBeatMessage message, WebSocketServer webSocketServer) throws IOException {
        webSocketServer.sendToUser(message.getFrom(),assemble(message));
    }

    @Override
    public String assemble(HeartBeatMessage message) {
        return JSON.toJSONString(
                new ShowMessage(
                        message.getTo(),
                        "heartbeat-received",
                        LocalDateTime.now()
                )
        );
    }
}
