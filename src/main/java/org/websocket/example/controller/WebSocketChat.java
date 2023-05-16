package org.websocket.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;

@Controller
@ServerEndpoint(value = "/echo.do")
public class WebSocketChat {
    private static final List<Session> sessions = new ArrayList<Session>();
//    private static final Logger logger = LoggerFactory.getLogger(WebSocketChat.class);

    public WebSocketChat() {
        System.out.println("웹소켓(서버) 객체생성");
    }

    @OnOpen
    public void onOpen(Session session) {
//        logger.info("Open session id : " + session.getId());
        try {
            final RemoteEndpoint.Basic basic = session.getBasicRemote();
            basic.sendText("대화방에 연결 되었습니다.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        sessions.add(session);
    }

    // 모든 세션에 메세지 전송
    private void sendAllSessionToMessage(Session self, String sender, String message) {
        try {
            for (Session session : WebSocketChat.sessions) {
                if (!self.getId().equals(session.getId())) {
                    session.getBasicRemote().sendText(sender + " : " + message);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 내가 입력하는 메세지
    @OnMessage
    public void onMessage(String message, Session session) {
        String sender = message.split(",")[1];
        message = message.split(",")[0];

//        logger.info("Message From " + sender + ": " + message);
        try {
            final RemoteEndpoint.Basic basic = session.getBasicRemote();
            basic.sendText("<나> : " + message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sendAllSessionToMessage(session, sender, message);
    }

    @OnError
    public void onError(Throwable e, Session session) {

    }

    @OnClose
    public void onClose(Session session) {
//        logger.info("Session " + session.getId() + " has ended");
        sessions.remove(session);
    }
}
