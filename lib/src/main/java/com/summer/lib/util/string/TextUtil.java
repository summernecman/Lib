package com.summer.lib.util.string;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.summer.lib.util.NullUtil;

/**
 * Created by ${viwmox} on 2017-03-03.
 */

public class TextUtil {
    private static TextUtil instance;

    public static TextUtil getInstance() {
        if (instance == null) {
            instance = new TextUtil();
        }
        return instance;
    }

    public void setText(TextView text, String str, int divide, int beforeColor, int afterColor) {
        if (NullUtil.isStrEmpty(str)) {
            text.setText("");
        }
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        ForegroundColorSpan beforespan = new ForegroundColorSpan(beforeColor);
        ForegroundColorSpan afterspan = new ForegroundColorSpan(afterColor);
        builder.setSpan(beforespan, 0, divide, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(afterspan, divide, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        text.setText(builder);
    }
}
