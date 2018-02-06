package com.fm.naka.platform.xmppbroker.biz;

import com.fm.naka.platform.xmppbroker.mapper.XmppMessageMapper;
import com.fm.naka.platform.xmppbroker.model.NakaLoginMessage;
import com.fm.naka.platform.xmppbroker.model.NakaRecievedMessage;
import com.fm.naka.platform.xmppbroker.model.NakaSendMessage;
import com.fm.naka.platform.xmppbroker.utils.NakaMessageConverter;
import com.fm.naka.platform.xmppbroker.utils.XmppCredential;
import com.fm.naka.platform.xmppbroker.utils.XmppManager;
import com.google.gson.Gson;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.EntityBareJid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;

@Component
public class ChatManager {

    private @Autowired XmppManager xmppManager;
    private @Autowired NakaMessageConverter nakaMessageConverter;

    public void init(NakaLoginMessage nakaMessage , WebSocketSession session) {
        try {
            XmppCredential xmppCredential = fetchXmppCredential(nakaMessage);
            xmppManager.init(new ChatMessageListener(session) , xmppCredential);
        } catch (Exception e) {
            //TODO think about exceptions
            throw new RuntimeException(e);
        }
    }

    private XmppCredential fetchXmppCredential(NakaLoginMessage nakaMessage) {
        return new XmppCredential(nakaMessage.getUsername() , nakaMessage.getPassword());
    }

    class ChatMessageListener implements IncomingChatMessageListener {

        private WebSocketSession session;

        public ChatMessageListener(WebSocketSession session) {
            this.session = session;
        }
        @Override
        public void newIncomingMessage(EntityBareJid entityBareJid, Message message, Chat chat) {
            try {
                NakaRecievedMessage nakaRecievedMessage = nakaMessageConverter.prepareNakaRecievedMessageModel(message);
                session.sendMessage(new TextMessage(new Gson().toJson(nakaRecievedMessage)));
            } catch (IOException e) {
                //TODO log
                throw new RuntimeException(e);
            }
        }
    }

    public void sendMessage(NakaSendMessage nakaSendMessage) {
        //TODO to = "user1@openfire"
        try {
            xmppManager.sendMessage(nakaSendMessage.getFrom() , nakaSendMessage.getTo(), nakaSendMessage.getBody());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
