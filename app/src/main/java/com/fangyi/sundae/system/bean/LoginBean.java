package com.fangyi.sundae.system.bean;

/**
 * ================================================
 * 作    者：FANGYI <87649669@qq.com>
 * 版    本：1.0.0
 * 日    期：2018/5/22
 * 说    明：
 * ================================================
 */
public class LoginBean {
    @Override
    public String toString() {
        return "LoginBean{" +
                "id=" + id +
                ", adrApikey='" + adrApikey + '\'' +
                ", name='" + name + '\'' +
                ", group=" + group +
                ", salt='" + salt + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    /**
     * id : 1466
     * adrApikey : 8986b52b9783f7ca07f834f84e2a7de1
     * name : 13220207320
     * group : -1
     * salt : kQ3ELu
     * mobile : 13220207320
     */

    private int id;
    private String adrApikey;
    private String name;
    private int group;
    private String salt;
    private String mobile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdrApikey() {
        return adrApikey;
    }

    public void setAdrApikey(String adrApikey) {
        this.adrApikey = adrApikey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
