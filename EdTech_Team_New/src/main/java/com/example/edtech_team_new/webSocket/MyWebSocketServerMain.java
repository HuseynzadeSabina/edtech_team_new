package com.example.edtech_team_new.webSocket;

import org.glassfish.tyrus.server.Server;

import org.glassfish.tyrus.server.Server;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
@ServerEndpoint("/")
public class MyWebSocketServerMain {

    public void startUp() {
        // Настраиваем параметры сервера (если требуется)
        Map<String, Object> properties = new HashMap<>();

        // Создаем сервер
        Server server = new Server("127.0.0.1", 8094, "/", properties, MyWebSocketServerMain.class);

        try {
            server.start();
            System.out.println("WebSocket server started at ws://localhost:8080/websockets");
            // Ожидание пользовательского ввода для остановки сервера

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received: " + message);
        try {
            session.getBasicRemote().sendText("Echo: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

