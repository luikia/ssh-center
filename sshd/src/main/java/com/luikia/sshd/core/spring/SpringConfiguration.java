package com.luikia.sshd.core.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by suateam on 2017/8/29.
 */
@Configuration
public class SpringConfiguration {

    @Value("${ssh.threadpool.core-pool-size}")
    private Integer corePoolSize;
    @Value("${ssh.threadpool.keep-alive-seconds}")
    private Integer keepAliveSeconds;
    @Value("${ssh.threadpool.max-pool-size}")
    private Integer maxPoolSize;
    @Value("${ssh.threadpool.queue-capacity}")
    private Integer queueCapacity;

    @Bean
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(this.corePoolSize.intValue());
        executor.setKeepAliveSeconds(this.keepAliveSeconds.intValue());
        executor.setMaxPoolSize(this.maxPoolSize.intValue());
        executor.setQueueCapacity(this.queueCapacity.intValue());
        return executor;
    }
}
