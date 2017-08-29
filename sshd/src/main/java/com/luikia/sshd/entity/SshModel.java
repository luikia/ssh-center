package com.luikia.sshd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.sshd.common.config.keys.AuthorizedKeyEntry;
import org.apache.sshd.common.config.keys.KeyUtils;
import org.apache.sshd.common.config.keys.PublicKeyEntryResolver;
import org.apache.sshd.common.digest.BuiltinDigests;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;

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
    @JsonIgnore
    public String getPublicKey() {
        return this.publicKey;
    }

    public SshFingerPrint getFingerPrint(){
        AuthorizedKeyEntry entry = AuthorizedKeyEntry.parseAuthorizedKeyEntry(this.publicKey);
        return new SshFingerPrint(entry);
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
