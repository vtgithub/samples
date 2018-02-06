package com.fm.naka.platform.xmppbroker.utils;

import com.fm.naka.platform.xmppbroker.pool.User;
import com.fm.naka.platform.xmppbroker.pool.XmppConnectionPool;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class XmppManager {

    private ChatManager chatManager;
    private @Autowired XmppConnectionPool xmppConnectionPool;

    public void init(IncomingChatMessageListener incomingChatMessageListener , XmppCredential xmppCredential)
            throws IOException, InterruptedException, XMPPException, SmackException {
        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword(xmppCredential.getUsername(), xmppCredential.getPassword())
                .setXmppDomain("localhost")
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .setHost("localhost")
                .setPort(5222)
                .build();
        AbstractXMPPConnection conn = new XMPPTCPConnection(config);
        conn.connect().login();
        chatManager = ChatManager.getInstanceFor(conn);
        chatManager.addIncomingListener(incomingChatMessageListener);
        xmppConnectionPool.add(new User(xmppCredential.getUsername()), conn);
    }

    public void sendMessage(String from , String to , String messageBody)
            throws XmppStringprepException, SmackException.NotConnectedException, InterruptedException {
        chatManager = ChatManager.getInstanceFor(xmppConnectionPool.get(new User(from)));
        Chat chat = chatManager.chatWith(JidCreate.entityBareFrom(to + "@localhost"));
        chat.send(messageBody);
    }
}
