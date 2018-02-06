package com.fm.naka.platform.xmppbroker;

import com.fm.naka.platform.xmppbroker.service.ChatManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {

	private @Autowired ApplicationContext context;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		try {
			ChatManagerService chatManagerService = context.getBean(ChatManagerService.class);
			chatManagerService.processMessage(message , session);
		}catch (Exception e){
			//TODO set aspect on this class
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("connect");
		//the messages will be broadcasted to all users.
		//sessions.add(session);
	}

}