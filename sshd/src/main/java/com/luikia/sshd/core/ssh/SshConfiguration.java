package com.luikia.sshd.core.ssh;

import com.luikia.sshd.dao.SshDao;
import org.apache.sshd.server.auth.pubkey.PublickeyAuthenticator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by suateam on 2017/8/28.
 */
@Configuration
public class SshConfiguration{
    @Value("${ssh.port:22}")
    private Integer port;
    @Value("${ssh.hostname:0.0.0.0}")
    private String hostname;
    @Value("${ssh.ip}")
    private String ip;

    @Bean
    public SshConfig sshConfig(){
        SshConfig config = new SshConfig();
        config.setHost(hostname);
        config.setPort(port);
        return config;
    }
    @Bean
    public PublickeyAuthenticator publickeyAuthenticator(SshDao sshDao){
        DBPublicKeyAuthenticator publickeyAuthenticator = new DBPublicKeyAuthenticator();
        publickeyAuthenticator.setIp(this.ip);
        publickeyAuthenticator.setSshDao(sshDao);
        return publickeyAuthenticator;
    }

    @Bean
    public SshDaemon sshDaemon(SshConfig sshConfig,PublickeyAuthenticator publickeyAuthenticator){
        SshDaemon daemon = new SshDaemon();
        daemon.setSshConfig(sshConfig);
        daemon.setPublickeyAuthenticator(publickeyAuthenticator);
        return daemon;
    }



}
