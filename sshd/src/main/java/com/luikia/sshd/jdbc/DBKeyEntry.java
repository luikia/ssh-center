package com.luikia.sshd.jdbc;

import com.luikia.sshd.core.ssh.SshConfig;
import com.luikia.sshd.entity.Host;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.sshd.common.config.keys.AuthorizedKeyEntry;
import org.apache.sshd.common.config.keys.KeyUtils;
import org.apache.sshd.common.config.keys.PublicKeyEntryResolver;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by luikia on 2017/5/30.
 */
public class DBKeyEntry {

    private SshConfig config;

    private static final String sql= "select hostname,username,publickey from tbl_public_key where username=? and hostname=?";



    /*public DBKeyEntry(SshConfig config) throws ClassNotFoundException {
        this.config = config;
        Class.forName(config.getDbDriver());
    }

    public boolean validatePublicKeys(String username,String hostname,PublicKey key){
        Connection conn = null;
        List<Host> hostlist = null;
        try {
            conn = DriverManager.getConnection(config.getDbConnection(),config.getDbUserNanme(),config.getDbPassword());
            QueryRunner qRunner = new QueryRunner();
            hostlist = qRunner.query(conn,sql, new BeanListHandler<>(Host.class),username,hostname);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        hostlist = Optional.ofNullable(hostlist).orElse(new ArrayList<>());
        List<AuthorizedKeyEntry> entries = hostlist.stream()
                .map(host ->  AuthorizedKeyEntry.parseAuthorizedKeyEntry(host.getPublickey())).collect(Collectors.toList());
        try {
            List<PublicKey> keySet =  AuthorizedKeyEntry.resolveAuthorizedKeys(PublicKeyEntryResolver.FAILING, entries);
            for(PublicKey auth_key:keySet){
                if(KeyUtils.compareKeys(auth_key,key)){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }*/

}
