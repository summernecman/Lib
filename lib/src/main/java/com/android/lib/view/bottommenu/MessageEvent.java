package com.android.lib.view.bottommenu;

public class MessageEvent {

    public int position;

    public String sender;

    public String dealer;//sender

    public Object data;

    public Object id;

    public boolean isme = true;

    public MessageEvent() {
    }

    public MessageEvent(String sender, String dealer, Object data) {
        this.sender = sender;
        this.dealer = dealer;
        this.data = data;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "position=" + position +
                ", sender='" + sender + '\'' +
                ", dealer='" + dealer + '\'' +
                ", data=" + data +
                ", id=" + id +
                '}';
    }
}