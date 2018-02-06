package com.fm.naka.platform.xmppbroker.model;

public enum Method {
    INIT(1),
    SEND(2),
    PING(3);

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    Method(int value) {
        this.value = value;
    }
}
