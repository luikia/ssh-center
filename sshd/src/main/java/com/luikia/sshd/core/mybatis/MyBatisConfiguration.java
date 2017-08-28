package com.luikia.sshd.core.mybatis;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by suateam on 2017/8/28.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(value = "com.luikia.sshd",annotationClass = Mapper.class)
public class MyBatisConfiguration {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String dbUser;
    @Value("${spring.datasource.password}")
    private String dbPass;
    @Value("${spring.datasource.driver-class-name}")
    private String dbDriver;
    @Value("${ssh.datasource.max-active-connections:20}")
    private int maxActiveConnections;
    @Value("${ssh.datasource.max-idle-connections:8}")
    private int maxIdleConnections;
    @Value("${ssh.datasource.max-checkout-time:120000}")
    private int maxCheckoutTime;
    @Value("${ssh.datasource.time-to-wait:20000}")
    private int timeToWait;
    @Value("${ssh.datasource.ping-query:select 1}")
    private String pingQuery;
    @Value("${ssh.datasource.ping-enabled:true}")
    private boolean pingEnabled;
    @Value("${ssh.datasource.connections-not-used-for:7200000}")
    private int connectionsNotUserdFor;

    @Bean
    public DataSource dataSource() {
        PooledDataSource dataSource = new PooledDataSource(this.dbDriver, this.dbUrl, this.dbUser, this.dbPass);
        dataSource.setPoolMaximumActiveConnections(this.maxActiveConnections);
        dataSource.setPoolMaximumIdleConnections(this.maxIdleConnections);
        dataSource.setPoolMaximumCheckoutTime(this.maxCheckoutTime);
        dataSource.setPoolTimeToWait(this.timeToWait);
        dataSource.setPoolPingQuery(this.pingQuery);
        dataSource.setPoolPingEnabled(this.pingEnabled);
        dataSource.setPoolPingConnectionsNotUsedFor(this.connectionsNotUserdFor);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        sfb.setDataSource(dataSource);
        SqlSessionFactory factory = sfb.getObject();
        factory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return factory;
    }

}
