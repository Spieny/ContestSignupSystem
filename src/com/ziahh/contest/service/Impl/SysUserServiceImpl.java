package com.ziahh.contest.service.Impl;

import com.ziahh.contest.dao.SysUserDao;
import com.ziahh.contest.dao.impl.SysUserDaoImpl;
import com.ziahh.contest.pojo.SysUser;
import com.ziahh.contest.service.SysUserService;
import com.ziahh.contest.util.MD5Util;

public class SysUserServiceImpl implements SysUserService {

    private SysUserDao sysUserDao = new SysUserDaoImpl();

    @Override
    public int register(SysUser sysUser) {
        //处理密码
        sysUser.setUserPwd(MD5Util.encrypt(sysUser.getUserPwd()));
        //调用DAO,将sysUser信息存入数据库
        return sysUserDao.register(sysUser);
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserDao.findUser(username);
    }

}
