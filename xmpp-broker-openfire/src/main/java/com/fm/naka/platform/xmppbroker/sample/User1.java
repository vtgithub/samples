package com.fm.naka.platform.xmppbroker.sample;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.EntityBareJid;

import java.io.IOException;

public class User1 {
    public static void main(String[] args)
            throws IOException, InterruptedException, XMPPException, SmackException {
        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword("user1", "123456")
                .setXmppDomain("openfire")
                .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                .setHost("openfire")
                .setPort(5222)
                .build();
        AbstractXMPPConnection conn = new XMPPTCPConnection(config);

        conn.connect().login();
        //
        ChatManager chatManager = ChatManager.getInstanceFor(conn);
        chatManager.addIncomingListener(new IncomingChatMessageListener() {

            public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
                System.out.println("New message from " + from + ": " + message.getBody());
            }
        });
//        OfflineMessageManager offlineMessageManager = new OfflineMessageManager(conn);
//        List<Message> messages = offlineMessageManager.getMessages();
//        System.out.println(messages.size());
        while(true);
    }
}