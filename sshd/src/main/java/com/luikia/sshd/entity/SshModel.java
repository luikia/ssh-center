package com.luikia.sshd.entity;

import org.apache.sshd.common.config.keys.KeyUtils;

/**
 * Created by luikia on 2017/8/28.
 */
public class SshModel {
    private Integer id;
    private String ip;
    private String userName;
    private String publicKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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



        return KeyUtils.getFingerPrint(publicKey);
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
