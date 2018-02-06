package com.fm.naka.platform.xmppbroker.mapper;

import com.fm.naka.platform.xmppbroker.model.XmppMessage;
import org.springframework.stereotype.Component;

@Component
public class XmppMessageMapper {
    public String getNakaMessage(XmppMessage xmppMessage) {
        return null;
    }

    public XmppMessage getXmppMessage(String payload) {
        return null;
    }

    //Map<String, String> value = new Gson().fromJson(message.getPayload(), Map.class);

}
