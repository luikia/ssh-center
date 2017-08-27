package com.luikia.sshd.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by luikia on 2017/5/30.
 */
public class SshConfig {

    private String host;

    private Integer port;

    private String keyPairFile;

    private String dbDriver;

    private String dbConnection;

    private String dbUserNanme;

    private String dbPassword;

    public SshConfig(Properties prop) {
        this.host = prop.getProperty("host","0.0.0.0");
        this.port = Integer.valueOf(prop.getProperty("port","22"));
        this.dbDriver = prop.getProperty("db_driver");
        this.dbConnection = prop.getProperty("db_connection");
        this.dbUserNanme = prop.getProperty("db_username");
        this.dbPassword = prop.getProperty("db_password");
        this.keyPairFile = prop.getProperty("key_pair_file");
    }


    public SshConfig(InputStream input) throws IOException {
        this(loadProperites(input));
    }

    public SshConfig(File file) throws IOException{
        this(new FileInputStream(file));
    }

    private static Properties loadProperites(InputStream input) throws IOException {
        Properties properties = new Properties();
        properties.load(input);
        return properties;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getKeyPairFile() {
        return keyPairFile;
    }

    public void setKeyPairFile(String keyPairFile) {
        this.keyPairFile = keyPairFile;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public String getDbConnection() {
        return dbConnection;
    }

    public void setDbConnection(String dbConnection) {
        this.dbConnection = dbConnection;
    }

    public String getDbUserNanme() {
        return dbUserNanme;
    }

    public void setDbUserNanme(String dbUserNanme) {
        this.dbUserNanme = dbUserNanme;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
}
