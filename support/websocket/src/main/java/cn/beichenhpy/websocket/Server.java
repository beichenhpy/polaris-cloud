package cn.beichenhpy.websocket;

import cn.beichenhpy.websocket.modal.Message;
import cn.beichenhpy.websocket.modal.MessageType;
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
import java.util.stream.Collectors;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote websocket 默认是多例的，每个session都是不一样的
 * @since 2021/7/31 14:36
 */
@Slf4j
@Component
@ServerEndpoint(value = "/chat/{userId}")
public class Server {

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
        Message message = JSON.parseObject(data, Message.class);
        sendMessage(message);
    }


    @OnError
    public void onError(@PathParam("userId") String userid, Session session, Throwable throwable) {
        log.info("[ChatServer] close : userid = " + userid + " , sessionId = " + session.getId() + " , throwable = " + throwable.getMessage());
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
     * 根据消息类型发送消息
     * @param message 消息
     * @throws IOException 异常
     */
    public void sendMessage(Message message) throws IOException {
        //防止枚举不识别
        switch (message.getType()) {
            case SINGLE:
                sendToUser(message.getTo(), message.getText());
                break;
            case ALL:
                sendAll(message.getText());
                break;
            case GROUP:
                //将用户加入群组
                joinGroup(message.getTo(),message.getFrom());
                //群发
                Set<String> groupMembers = getGroupMembers(message.getTo());
                for (String groupMember : groupMembers) {
                    sendToUser(groupMember,message.getText());
                }
                break;
            default:
        }
    }

    /**
     * 发送给某个用户
     *
     * @param userId 用户
     * @param text    消息
     * @throws IOException io异常
     */
    public void sendToUser(String userId, String text) throws IOException {
        ONLINE_USERS.get(userId).getBasicRemote().sendText(text);
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
     * @param groupId 群组名
     * @param userId 用户名
     */
    public void joinGroup(String groupId,String userId){
        //判断是否有key
        if (GROUPS.containsKey(groupId)) {
            Set<Map.Entry<String, Set<String>>> groups = GROUPS.entrySet();
            for (Map.Entry<String, Set<String>> group : groups) {
                Set<String> users = group.getValue();
                users.add(userId);
            }
        }else {
            Set<String> users = new CopyOnWriteArraySet<>();
            users.add(userId);
            GROUPS.put(groupId, users);
        }
    }

    /**
     * 获取群员
     * @param groupId 群id
     * @return 用户ids
     */
    public Set<String> getGroupMembers(String groupId){
        return GROUPS.get(groupId);
    }
}
