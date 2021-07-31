package cn.beichenhpy.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/7/31 14:36
 */
@Slf4j
@Component
@ServerEndpoint(value = "/chat/{userId}")
public class Server {

    private static Map<String,Session> onlineUsers = new ConcurrentSkipListMap<>();
    private static Integer onlineCount = 0;

    @OnOpen
    public void onOpen(@PathParam("userId")String userId, Session session){
        onlineUsers.put(userId,session);
        onlineCount ++;
        log.info("[user]:{},online:{}",userId,onlineCount);
    }

    @OnClose
    public void onClose(@PathParam("userId")String userId, Session session, CloseReason closeReason){
        log.info("[ChatServer] close : userid = " + userId + " , sessionId = " + session.getId() +
                " , closeCode = " + closeReason.getCloseCode().getCode() + " , closeReason = " +closeReason.getReasonPhrase());
        onlineUsers.remove(userId);
        onlineCount--;
    }

    @OnMessage
    public void onMessage(@PathParam("userid") String userid,String data){
        log.info("user:{},data:{}",userid,data);
    }


    @OnError
    public void onError(@PathParam("userId") String userid,Session session,Throwable throwable){
        log.info("[ChatServer] close : userid = " + userid + " , sessionId = " + session.getId() +" , throwable = " + throwable.getMessage() );
    }

}
