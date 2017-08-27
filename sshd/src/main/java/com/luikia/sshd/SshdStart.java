package com.luikia.sshd;

import com.luikia.sshd.config.SshConfig;
import com.luikia.sshd.jdbc.DBKeyEntry;
import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.scp.ScpCommandFactory;
import org.apache.sshd.server.shell.ProcessShellFactory;
import org.apache.sshd.server.subsystem.sftp.SftpSubsystemFactory;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;


public class SshdStart {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String configPath =  args==null ? null : args[0];
        SshConfig config = new SshConfig(new File(configPath));
        SshServer sshd = SshServer.setUpDefaultServer();
        DBKeyEntry entry = new DBKeyEntry(config);
        String hostname = InetAddress.getLocalHost().getHostName();
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(new File(config.getKeyPairFile())));
        sshd.setShellFactory(new ProcessShellFactory(new String[] { "/bin/bash","-i","-l" }));
        sshd.setPublickeyAuthenticator((username, key, session) -> entry.validatePublicKeys(username,hostname,key));
        ScpCommandFactory scp = new ScpCommandFactory.Builder().withDelegate(command -> new ProcessShellFactory(command.split(" ")).create()).build();
        sshd.setCommandFactory(scp);
        List<NamedFactory<Command>> subsystemFactories = new ArrayList<>(1);
        subsystemFactories.add(new SftpSubsystemFactory.Builder().build());
        sshd.setSubsystemFactories(subsystemFactories);
        sshd.setPort(8888);
        sshd.setHost("0.0.0.0");
        sshd.start();
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sshd.close();
        }



    }
}
