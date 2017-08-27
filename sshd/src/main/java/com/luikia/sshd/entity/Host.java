package com.luikia.sshd.entity;

/**
 * Created by luikia on 2017/5/30.
 */
public class Host {

    private String hostname;

    private String username;

    private String publickey;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPublickey() {
        return publickey;
    }

    public void setPublickey(String publickey) {
        this.publickey = publickey;
    }

    public String getHostname() {

        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
