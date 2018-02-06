package com.fm.naka.platform.xmppbroker.utils;

public class XmppCredential {
    private String username;
    private String password;

    public XmppCredential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
