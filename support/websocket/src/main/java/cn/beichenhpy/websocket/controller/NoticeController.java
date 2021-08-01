package cn.beichenhpy.websocket.controller;

import cn.beichenhpy.websocket.WebSocketServer;
import cn.beichenhpy.websocket.modal.NoticeLevel;
import cn.beichenhpy.websocket.modal.SendToType;
import cn.beichenhpy.websocket.modal.body.NoticeMessage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/1 19:56
 */
@RestController
public class NoticeController {

    @Resource
    private WebSocketServer webSocketServer;


    @PostMapping("/noticeAll")
    public void noticeAll(){
        try {
            NoticeMessage noticeMessage = new NoticeMessage();
            noticeMessage.setLevel(NoticeLevel.HIGH);
            noticeMessage.setFrom("admin");
            noticeMessage.setTo("all");
            noticeMessage.setType(SendToType.ALL);
            noticeMessage.setContent("严重");
            webSocketServer.notice(noticeMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/notice/{userId}")
    public void noticeToUser(@PathVariable("userId") String userId){
        try {
            NoticeMessage noticeMessage = new NoticeMessage();
            noticeMessage.setLevel(NoticeLevel.HIGH);
            noticeMessage.setFrom("admin");
            noticeMessage.setTo(userId);
            noticeMessage.setType(SendToType.SINGLE);
            noticeMessage.setContent("严重");
            webSocketServer.notice(noticeMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
