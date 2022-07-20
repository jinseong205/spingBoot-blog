package com.jinseong.blog.handler;

import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {

	HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
	
	//socket Connected
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
	}

	//socket Closed
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, status);
	}
	
	//massageSend
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try {
				wss.sendMessage(new TextMessage(msg));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		super.handleTextMessage(session, message);
	}
}
