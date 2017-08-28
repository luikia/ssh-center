package com.luikia.sshd.core.ssh;

import org.apache.sshd.server.auth.pubkey.PublickeyAuthenticator;
import org.apache.sshd.server.session.ServerSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.PublicKey;
import java.util.Optional;

/**
 * Created by suateam on 2017/8/28.
 */
@Component
public class DBPublicKeyAuthenticator implements PublickeyAuthenticator {
    @Value("${ssh.ip}")
    private String ip;

    @Override
    public boolean authenticate(String username, PublicKey key, ServerSession session) {

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
}
