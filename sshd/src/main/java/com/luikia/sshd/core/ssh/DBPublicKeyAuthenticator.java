package com.luikia.sshd.core.ssh;

import com.luikia.sshd.dao.SshDao;
import org.apache.sshd.common.config.keys.AuthorizedKeyEntry;
import org.apache.sshd.common.config.keys.KeyUtils;
import org.apache.sshd.common.config.keys.PublicKeyEntryResolver;
import org.apache.sshd.server.auth.pubkey.PublickeyAuthenticator;
import org.apache.sshd.server.session.ServerSession;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by suateam on 2017/8/28.
 */
public class DBPublicKeyAuthenticator implements PublickeyAuthenticator {

    private String ip;

    private SshDao sshDao;

    @Override
    public boolean authenticate(String username, PublicKey key, ServerSession session) {
        try{
            List<String> keys = sshDao.getPublicKey(this.ip,username);
            List<AuthorizedKeyEntry> entries = keys
                    .stream()
                    .map(k ->  AuthorizedKeyEntry.parseAuthorizedKeyEntry(k))
                    .collect(Collectors.toList());
            List<PublicKey> keySet
                    = AuthorizedKeyEntry.resolveAuthorizedKeys(PublicKeyEntryResolver.FAILING, entries);
            for(PublicKey auth_key:keySet){
                if(KeyUtils.compareKeys(auth_key,key)){
                    return true;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public String getIp() {
        try {
            return Optional.ofNullable(ip).orElse(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setSshDao(SshDao sshDao) {
        this.sshDao = sshDao;
    }
}
