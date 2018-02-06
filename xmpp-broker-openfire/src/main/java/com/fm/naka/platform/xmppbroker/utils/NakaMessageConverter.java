package com.fm.naka.platform.xmppbroker.utils;

import com.fm.naka.platform.xmppbroker.model.NakaRecievedMessage;
import org.jivesoftware.smack.packet.Message;
import org.springframework.stereotype.Component;

@Component
public class NakaMessageConverter {
    public NakaRecievedMessage prepareNakaRecievedMessageModel(Message message){
        NakaRecievedMessage nakaRecievedMessage = new NakaRecievedMessage();
        nakaRecievedMessage.setSubject(message.getSubject());
        nakaRecievedMessage.setBody(message.getBody());
        nakaRecievedMessage.setFrom(message.getFrom().asBareJid().getLocalpartOrNull().toString());
        return nakaRecievedMessage;
    }
}
