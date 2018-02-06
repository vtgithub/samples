package com.fm.naka.platform.xmppbroker.pool;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class XmppConnectionPool {

    private Map<User, AbstractXMPPConnection> connectionMap = new ConcurrentHashMap<>();

    public void add(User user, AbstractXMPPConnection connection) {
        connectionMap.put(user, connection);
    }

    public AbstractXMPPConnection get(User user){
        return connectionMap.get(user);
    }
}
