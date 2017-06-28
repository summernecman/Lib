package com.summer.desktop.module.classify.simple;

import com.summer.desktop.module.classify.adapter.BaseMainAdapter;
import com.summer.desktop.module.classify.adapter.BaseSubAdapter;

/**
 * Version 1.0
 * <p>
 * Date: 16/6/7 12:00
 * Author: rsshinide38@163.com
 */
public interface BaseSimpleAdapter {
    BaseMainAdapter getMainAdapter();

    BaseSubAdapter getSubAdapter();

    boolean isShareViewPool();
}
