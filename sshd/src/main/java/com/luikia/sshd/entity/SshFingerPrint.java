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
 * Created by suateam on 2017/8/29.
 */
public class SshFingerPrint {

    private String type;

    private String comment;

    private AuthorizedKeyEntry entry;

    public SshFingerPrint(AuthorizedKeyEntry entry){
        this.type = entry.getKeyType();
        this.comment = entry.getComment();
        this.entry = entry;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFingerPrint() {
        try {
            PublicKey key = this.entry.resolvePublicKey(PublicKeyEntryResolver.FAILING);
            String fp = KeyUtils.getFingerPrint(BuiltinDigests.md5,key);
            return fp.replace(BuiltinDigests.Constants.MD5.toUpperCase()+":","");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    @JsonIgnore
    public AuthorizedKeyEntry getEntry() {
        return entry;
    }

    public void setEntry(AuthorizedKeyEntry entry) {
        this.entry = entry;
    }
}
