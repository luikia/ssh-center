package com.luikia.sshd.controller;

import com.luikia.sshd.model.request.SshModelRequest;
import com.luikia.sshd.entity.SshModel;
import com.luikia.sshd.service.SshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by suateam on 2017/8/28.
 */
@Controller
@RequestMapping("/ssh")
public class SshController {
    @Autowired
    private SshService sshService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public List<SshModel> sshModelList(@RequestParam String ip){
        SshModelRequest req = new SshModelRequest();
        req.setIp(ip);
        return sshService.list(req);
    }


}
