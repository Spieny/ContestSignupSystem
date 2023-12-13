package com.ziahh.contest.dao.impl;

import com.ziahh.contest.dao.BaseDao;
import com.ziahh.contest.dao.SysUserDao;
import com.ziahh.contest.pojo.SysUser;

import java.util.List;

public class SysUserDaoImpl extends BaseDao implements SysUserDao {
    @Override
    public int register(SysUser sysUser) {
        String sql = "insert into sys_user values(DEFAULT,?,?)";
        int rows = baseUpdate(sql,sysUser.getUserName(),sysUser.getUserPwd());
        return 0;
    }

    @Override
    public SysUser findUser(String userName) {
        String sql = "select uid,userName,userPwd from sys_user where userName = ?";
        List<SysUser> userList = baseQuery(SysUser.class,sql,userName);
        return userList != null && userList.size() > 0 ? userList.get(0) : null;
    }
}
