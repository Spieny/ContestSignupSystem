package com.ziahh.contest.service.Impl;

import com.ziahh.contest.dao.SysUserDao;
import com.ziahh.contest.dao.impl.SysUserDaoImpl;
import com.ziahh.contest.pojo.SysContest;
import com.ziahh.contest.pojo.SysUpdate;
import com.ziahh.contest.pojo.SysUser;
import com.ziahh.contest.service.SysUserService;
import com.ziahh.contest.util.MD5Util;

import java.util.List;

public class SysUserServiceImpl implements SysUserService {

    private SysUserDao sysUserDao = new SysUserDaoImpl();

    @Override
    public int register(SysUser sysUser) {
        //处理密码
        sysUser.setUserPwd(MD5Util.encrypt(sysUser.getUserPwd()));
        sysUser.setUserClass("未设置");
        //调用DAO,将sysUser信息存入数据库
        return sysUserDao.register(sysUser);
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserDao.findUser(username);
    }

    @Override
    public int updateClass(SysUpdate update) {
        //处理密码
        return sysUserDao.updateClass(update);
    }

    @Override
    public int updatePassword(SysUpdate update) {
        //处理密码
        update.setUpdatePwd(MD5Util.encrypt(update.getUpdatePwd()));
        return sysUserDao.updatePassword(update);
    }

    @Override
    public List<SysContest> findEnrolledContest(SysUser user) {
        return sysUserDao.getEnrolledContest(user);
    }


}
