package com.summer.desktop.module.app.folder;

import java.util.List;

/**
 * Created by lizhiming211223 on 2016/12/29.
 */
public class BaseBean {

    public boolean isGroup;
    List<BookBean> bookList;


    public BaseBean(List<BookBean> bookList) {
        this.bookList = bookList;
    }

    public BaseBean() {
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setIsGroup(boolean isGroup) {
        this.isGroup = isGroup;
    }

    public List<BookBean> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookBean> bookList) {
        this.bookList = bookList;
    }

}
