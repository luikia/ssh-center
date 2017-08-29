package com.luikia.sshd.model.request;

/**
 * Created by suateam on 2017/8/29.
 */
public class SshModelRequest {

    public String ip;

    public String userName;

    public String publicKey;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
