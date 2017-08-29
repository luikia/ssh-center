package com.luikia.sshd.dao;

import com.luikia.sshd.core.mybatis.Mapper;
import com.luikia.sshd.entity.SshModel;
import com.luikia.sshd.model.request.SshModelRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by suateam on 2017/8/29.
 */
@Mapper
public interface SshDao {

    @Select("SELECT t.id, t.ip, t.user_name, t.public_key FROM tbl_ssh_key t where t.ip=#{req.ip}")
    List<SshModel> list(@Param("req") SshModelRequest request);
    @Select("SELECT t.public_key FROM tbl_ssh_key t where t.ip=#{ip} and t.user_name = #{user}")
    List<String> getPublicKey(@Param("ip") String ip,@Param("user") String user);

}
