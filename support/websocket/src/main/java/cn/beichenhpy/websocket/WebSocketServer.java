package cn.beichenhpy.websocket;

import cn.beichenhpy.exception.user.UserNotFoundException;
import cn.beichenhpy.websocket.modal.Message;
import cn.beichenhpy.websocket.modal.body.ChatMessage;
import cn.beichenhpy.websocket.modal.body.NoticeMessage;
import cn.beichenhpy.websocket.service.impl.ChatService;
import cn.beichenhpy.websocket.service.IMessageService;
import cn.beichenhpy.websocket.service.impl.NoticeService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote websocket 默认是多例的，每个session都是不一样的
 * @since 2021/7/31 14:36
 */
@Slf4j
@Component
@ServerEndpoint(value = "/{userId}")
public class WebSocketServer {

    private static final Map<String, Session> ONLINE_USERS = new ConcurrentHashMap<>();
    private static Integer onlineCount = 0;
    //群组 groupId,userIds
    private static final Map<String, Set<String>> GROUPS = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        addOnlineCount(userId);
        ONLINE_USERS.put(userId, session);
        log.info("[user]:{},online:{},session:{}", userId, onlineCount, session.getId());
    }

    @OnClose
    public void onClose(@PathParam("userId") String userId, Session session, CloseReason closeReason) {
        log.info("[ChatServer] close : userid = " + userId + " , sessionId = " + session.getId() +
                " , closeCode = " + closeReason.getCloseCode().getCode() + " , closeReason = " + closeReason.getReasonPhrase());
        onlineCount--;
        ONLINE_USERS.remove(userId);
    }

    @OnMessage
    public void onMessage(@PathParam("userId") String userId, String data) throws IOException {
        log.info("user:{},data:{}", userId, data);
        //parse to Message
        Message message = JSON.parseObject(data, Message.class);
        //根据消息头类型不同，进行筛选
        switch (message.getHeader()) {
            case CHAT:
                chat(JSON.parseObject(message.getBody(), ChatMessage.class));
                break;
            case NOTICE:
                notice(JSON.parseObject(message.getBody(), NoticeMessage.class));
                break;
            case HEARTBEAT:
                heartbeat();
                break;
            default:
        }
    }


    @OnError
    public void onError(@PathParam("userId") String userid, Session session, Throwable throwable) {
        log.info("[ChatServer] close : userid = " + userid + " , sessionId = " + session.getId() + " , throwable = " + throwable.getMessage());
    }

    /**
     * 聊天
     * @param chatMessage 聊天信息
     * @throws IOException 异常
     */
    public void chat(ChatMessage chatMessage) throws IOException{
        //do sendMessage
        IMessageService<ChatMessage> chatService = new ChatService();
        chatService.sendMessage(chatMessage,this);
    }


    /**
     * 通知
     * @param noticeMessage 通知信息
     * @throws IOException 异常
     */
    public void notice(NoticeMessage noticeMessage) throws IOException {
        //do sendMessage
        IMessageService<NoticeMessage> noticeService = new NoticeService();
        noticeService.sendMessage(noticeMessage,this);
    }

    /**
     * 心跳
     * @throws IOException 异常
     */
    public void heartbeat() throws IOException{
        //todo design
        log.info("heartbeat");
    }
    /**
     * 添加在线人数
     *
     * @param userId 用户id
     */
    private void addOnlineCount(String userId) {
        if (ONLINE_USERS.entrySet().size() == 0) {
            onlineCount++;
        } else {
            if (!ONLINE_USERS.containsKey(userId)) {
                onlineCount++;
            }
        }
    }


    /**
     * 发送给某个用户
     *
     * @param userId 用户
     * @param text   消息
     * @throws IOException io异常
     */
    public void sendToUser(String userId, String text) throws IOException {
        //fix:userId不存在的NollPointer
        if (ONLINE_USERS.containsKey(userId)){
            ONLINE_USERS.get(userId).getBasicRemote().sendText(text);
        }else {
            throw new UserNotFoundException("发送消息的对象不存在");
        }
    }

    /**
     * 群发
     *
     * @param text 消息
     * @throws IOException io异常
     */
    public void sendAll(String text) throws IOException {
        for (Map.Entry<String, Session> users : ONLINE_USERS.entrySet()) {
            Session session = users.getValue();
            session.getBasicRemote().sendText(text);
        }
    }

    /**
     * 加群
     *
     * @param groupId 群组名
     * @param userId  用户名
     */
    public void joinGroup(String groupId, String userId) {
        //判断是否有key
        if (GROUPS.containsKey(groupId)) {
            Set<Map.Entry<String, Set<String>>> groups = GROUPS.entrySet();
            for (Map.Entry<String, Set<String>> group : groups) {
                Set<String> users = group.getValue();
                users.add(userId);
            }
        } else {
            Set<String> users = new CopyOnWriteArraySet<>();
            users.add(userId);
            GROUPS.put(groupId, users);
        }
    }

    /**
     * 获取群员
     *
     * @param groupId 群id
     * @return 用户ids
     */
    public Set<String> getGroupMembers(String groupId) {
        return GROUPS.get(groupId);
    }
}
