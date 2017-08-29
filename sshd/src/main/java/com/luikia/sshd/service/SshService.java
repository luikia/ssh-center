package com.luikia.sshd.service;

import com.luikia.sshd.core.ssh.SshDaemon;
import com.luikia.sshd.dao.SshDao;
import com.luikia.sshd.entity.SshModel;
import com.luikia.sshd.model.request.SshModelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by suateam on 2017/8/29.
 */
@Service
@Transactional
public class SshService {
    @Autowired
    private SshDao sshDao;
    @Autowired
    private SshDaemon sshDaemon;
    @Autowired
    private Executor threadPoolTaskExecutor;

    public List<SshModel> list(SshModelRequest request){
        return sshDao.list(request);
    }

    @PostConstruct
    public void startSsh(){
        threadPoolTaskExecutor.execute(sshDaemon);
    }

}
