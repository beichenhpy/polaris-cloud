package cn.beichenhpy.websocket.service.impl;

import cn.beichenhpy.websocket.WebSocketServer;
import cn.beichenhpy.websocket.modal.NoticeLevel;
import cn.beichenhpy.websocket.modal.body.NoticeMessage;
import cn.beichenhpy.websocket.modal.show.NoticeShowMessage;
import cn.beichenhpy.websocket.service.IMessageService;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/1 19:00
 */
public class NoticeService implements IMessageService<NoticeMessage> {
    @Override
    public void sendMessage(NoticeMessage message, WebSocketServer webSocketServer) throws IOException {
        switch (message.getType()) {
            case SINGLE:
                webSocketServer.sendToUser(message.getTo(), assemble(message));
                break;
            case ALL:
                webSocketServer.sendAll(assemble(message));
                break;
            case GROUP:
                //将用户加入群组
                webSocketServer.joinGroup(message.getTo(), message.getFrom());
                //群发
                Set<String> groupMembers = webSocketServer.getGroupMembers(message.getTo());
                for (String groupMember : groupMembers) {
                    webSocketServer.sendToUser(groupMember, assemble(message));
                }
                break;
            default:
        }
    }

    @Override
    public String assemble(NoticeMessage message) {
        return JSON.toJSONString(
                new NoticeShowMessage(
                        message.getFrom(),
                        message.getContent(),
                        LocalDateTime.now(),
                        message.getLevel()
                )
        );
    }
}
