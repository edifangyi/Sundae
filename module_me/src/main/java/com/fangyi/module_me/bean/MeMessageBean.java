package com.fangyi.module_me.bean;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/6/7
 * 说    明：
 * ================================================
 */
public class MeMessageBean {
    private int tag;
    private int icon;
    private String title;
    private String url;

    public MeMessageBean(int tag, int icon, String title, String url) {
        this.tag = tag;
        this.icon = icon;
        this.title = title;
        this.url = url;
    }

    public MeMessageBean(int tag, int icon, String title) {
        this.tag = tag;
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
