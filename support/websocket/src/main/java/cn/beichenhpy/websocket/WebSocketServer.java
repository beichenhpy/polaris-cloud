package cn.beichenhpy.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;

import org.springframework.stereotype.Component;

import cn.beichenhpy.exception.user.UserNotFoundException;
import cn.beichenhpy.websocket.modal.Message;
import cn.beichenhpy.websocket.modal.body.ChatMessage;
import cn.beichenhpy.websocket.modal.body.HeartBeatMessage;
import cn.beichenhpy.websocket.modal.body.NoticeMessage;
import cn.beichenhpy.websocket.service.impl.ChatService;
import cn.beichenhpy.websocket.service.impl.HeartBeatService;
import cn.beichenhpy.websocket.service.impl.NoticeService;
import lombok.extern.slf4j.Slf4j;

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

    private volatile ChatService chatService;
    private volatile NoticeService noticeService;
    private volatile HeartBeatService heartBeatService;

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
                heartbeat(JSON.parseObject(message.getBody(), HeartBeatMessage.class));
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
     *
     * @param chatMessage 聊天信息
     * @throws IOException 异常
     */
    public void chat(ChatMessage chatMessage) throws IOException {
        //do sendMessage
        if (chatService == null) {
            synchronized (WebSocketServer.class) {
                if (chatService == null) {
                    chatService = new ChatService();
                }
            }
        }
        log.info("user:{},chatService:{}",chatMessage.getFrom(),chatService);
        chatService.sendMessage(chatMessage, this);
    }


    /**
     * 通知
     *
     * @param noticeMessage 通知信息
     * @throws IOException 异常
     */
    public void notice(NoticeMessage noticeMessage) throws IOException {
        //do sendMessage
        if (noticeService == null) {
            synchronized (WebSocketServer.class) {
                if (noticeService == null) {
                    noticeService = new NoticeService();
                }
            }
        }
        log.info("user:{},noticeService:{}",noticeMessage.getFrom(),noticeService);
        noticeService.sendMessage(noticeMessage, this);
    }

    /**
     * 心跳
     *
     * @throws IOException 异常
     */
    public void heartbeat(HeartBeatMessage heartBeatMessage) throws IOException {
        if (heartBeatService == null) {
            synchronized (WebSocketServer.class) {
                if (heartBeatService == null) {
                    heartBeatService = new HeartBeatService();
                }
            }
        }
        log.info("user:{},heartService:{}",heartBeatMessage.getFrom(),heartBeatService);
        heartBeatService.sendMessage(heartBeatMessage, this);
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
        if (ONLINE_USERS.containsKey(userId)) {
            ONLINE_USERS.get(userId).getBasicRemote().sendText(text);
        } else {
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
