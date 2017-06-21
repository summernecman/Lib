package com.summer.lib.view.bottommenu;

public class MessageEvent {

    public int position;

    public String sender;

    public String dealer;//sender

    public Object data;

    public Object id;

    public MessageEvent() {
    }

    public MessageEvent(String sender, String dealer, Object data) {
        this.sender = sender;
        this.dealer = dealer;
        this.data = data;
    }
}