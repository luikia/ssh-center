package com.luikia.sshd;

import com.luikia.sshd.core.ssh.SshConfig;
import com.luikia.sshd.jdbc.DBKeyEntry;
import org.apache.sshd.client.config.hosts.KnownHostDigest;
import org.apache.sshd.common.NamedFactory;
import org.apache.sshd.common.config.keys.AuthorizedKeyEntry;
import org.apache.sshd.common.config.keys.KeyUtils;
import org.apache.sshd.common.config.keys.PublicKeyEntryResolver;
import org.apache.sshd.common.digest.BaseDigest;
import org.apache.sshd.common.digest.BuiltinDigests;
import org.apache.sshd.common.digest.DigestUtils;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.scp.ScpCommandFactory;
import org.apache.sshd.server.shell.ProcessShellFactory;
import org.apache.sshd.server.subsystem.sftp.SftpSubsystemFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SshdStart {

    public static void main(String[] args) throws Exception {
       /* AuthorizedKeyEntry entry = AuthorizedKeyEntry.parseAuthorizedKeyEntry("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCy62rNp+dKwff/fjNPSFgkTaMNQVNEBRd4cJf2ehYhQPcEzauavVKvyxU/dIvv+pOsdMt/0JwkAVrTDccwTTyZhqI+mw/f/n+DBkH/oQ448iMgVUDiKno1HQhAnHv1vf58p8UfdVZEwSjNETq6YO1OqZqD58qJeKYdXcxDZ/F7Sl5j02ffD74P4kMiltnnaaMU9ExNaPHaUFoP5sh9hEVfaG52IOi0JBaWMn4J4K4Dn1dgnbwz+rwYMaStti/xXyZHVuAq7CjhH8rS/47dDeKcMpV779B1bOoMUkZ7qt2UZ5NqAJpkVXGnpRCjhiC1o0UH+8WX+i5qUka7LiDzxPSb suateam@DESKTOP-27I6UCL");
        PublicKey key = entry.resolvePublicKey(PublicKeyEntryResolver.FAILING);

        String fp = KeyUtils.getFingerPrint(BuiltinDigests.md5,key);
        System.out.println(fp);*/



        SpringApplication.run(SshdStart.class,args);

    }



    /*public static void startSsh(String[] args) throws IOException, ClassNotFoundException {
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
    }*/

}
