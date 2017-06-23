package com.summer.desktop.util;

//by summer on 2017-05-26.

import com.android.lib.view.bottommenu.MessageEvent;
import com.summer.desktop.module.note.main.NoteMainFrag;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class TitleUtil {
    private static TitleUtil instance;

    private ArrayList<String> name = new ArrayList<>();

    public static TitleUtil getInstance() {
        if (instance == null) {
            instance = new TitleUtil();
        }
        return instance;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public String getStr() {
        String ss = "";
        for (int i = 0; i < TitleUtil.getInstance().getName().size(); i++) {
            ss += TitleUtil.getInstance().getName().get(i) + "/";
        }
        return ss;
    }

    public void add(String name) {
        getName().add(name);
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.dealer = NoteMainFrag.class.getName();
        EventBus.getDefault().post(messageEvent);
    }

    public void remove(String name) {
        getName().remove(TitleUtil.getInstance().getName().get(TitleUtil.getInstance().getName().size() - 1));
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.dealer = NoteMainFrag.class.getName();
        EventBus.getDefault().post(messageEvent);
        EventBus.getDefault().post(messageEvent);
    }
}
