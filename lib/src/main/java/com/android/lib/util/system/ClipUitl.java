package com.android.lib.util.system;

//by summer on 2017-06-27.

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.android.lib.util.LogUtil;

public class ClipUitl {

    private static ClipUitl instance;

    public static ClipUitl getInsance() {
        if (instance == null) {
            instance = new ClipUitl();
        }
        return instance;
    }

    /**
     * 获取剪贴板文字
     *
     * @param context
     * @return
     */
    public String getClipTxt(Context context) {

        ClipboardManager myClipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = myClipboard.getPrimaryClip();
        if (clipData == null) {
            return "";
        }
        int itemcount = clipData.getItemCount();
        for (int i = 0; i < itemcount; i++) {
            LogUtil.E(clipData.getItemAt(i).getText());
        }
        if (itemcount > 0) {
            ClipData.Item item = clipData.getItemAt(0);
            return item.getText() == null ? "" : item.getText().toString();
        }
        return "";
    }
}
