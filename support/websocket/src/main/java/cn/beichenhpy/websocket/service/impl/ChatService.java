package cn.beichenhpy.websocket.service.impl;

import cn.beichenhpy.websocket.WebSocketServer;
import cn.beichenhpy.websocket.modal.body.ChatMessage;
import cn.beichenhpy.websocket.service.IMessageService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote 聊天业务
 * @since 2021/8/1 18:24
 */
public class ChatService implements IMessageService<ChatMessage> {
    @Override
    public void sendMessage(ChatMessage message, WebSocketServer webSocketServer) throws IOException {
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


}
