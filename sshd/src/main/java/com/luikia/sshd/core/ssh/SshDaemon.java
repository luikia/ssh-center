package com.luikia.sshd.core.ssh;

import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.auth.pubkey.PublickeyAuthenticator;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.scp.ScpCommandFactory;
import org.apache.sshd.server.shell.ProcessShellFactory;
import org.apache.sshd.server.subsystem.sftp.SftpSubsystemFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by suateam on 2017/8/29.
 */
public class SshDaemon implements Runnable{

    private SshConfig sshConfig;

    private PublickeyAuthenticator publickeyAuthenticator;

    @Override
    public void run() {
        SshServer sshd = SshServer.setUpDefaultServer();
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
        sshd.setShellFactory(new ProcessShellFactory(new String[] { "cmd" }));
        sshd.setPublickeyAuthenticator(this.publickeyAuthenticator);
        ScpCommandFactory scp = new ScpCommandFactory.Builder()
                .withDelegate(command -> new ProcessShellFactory(command.split(" ")).create()).build();
        sshd.setCommandFactory(scp);
        List<NamedFactory<Command>> subsystemFactories = new ArrayList<>(1);
        subsystemFactories.add(new SftpSubsystemFactory.Builder().build());
        sshd.setSubsystemFactories(subsystemFactories);
        sshd.setPort(this.sshConfig.getPort());
        sshd.setHost(this.sshConfig.getHost());
        try {
            sshd.start();
            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                sshd.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public PublickeyAuthenticator getPublickeyAuthenticator() {
        return publickeyAuthenticator;
    }

    public void setPublickeyAuthenticator(PublickeyAuthenticator publickeyAuthenticator) {
        this.publickeyAuthenticator = publickeyAuthenticator;
    }

    public SshConfig getSshConfig() {
        return sshConfig;
    }

    public void setSshConfig(SshConfig sshConfig) {
        this.sshConfig = sshConfig;
    }


}
