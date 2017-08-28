package com.luikia.sshd.core.ssh;

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
    @Bean
    public SshConfig sshConfig(){
        SshConfig config = new SshConfig();
        config.setHost(hostname);
        config.setPort(port);
        return config;
    }



}
