package com.summer.desktop.bean.dabean;

//by summer on 2017-06-07.

public class NoteListMsg {
    private int index;

    private Note note;

    public NoteListMsg(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
