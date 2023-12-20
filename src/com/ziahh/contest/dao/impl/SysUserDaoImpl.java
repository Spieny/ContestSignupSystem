package com.ziahh.contest.dao.impl;

import com.ziahh.contest.dao.BaseDao;
import com.ziahh.contest.dao.SysUserDao;
import com.ziahh.contest.pojo.SysContest;
import com.ziahh.contest.pojo.SysUpdate;
import com.ziahh.contest.pojo.SysUser;

import java.util.List;

public class SysUserDaoImpl extends BaseDao implements SysUserDao {
    @Override
    public int register(SysUser sysUser) {
        String sql = "insert into sys_user values(DEFAULT,?,?,?)";
        int rows = baseUpdate(sql,sysUser.getUserName(),sysUser.getUserPwd(),"未设置");
        return rows;
    }

    @Override
    public SysUser findUser(String userName) {
        String sql = "select uid,userName,userPwd,userClass from sys_user where userName = ?";
        List<SysUser> userList = baseQuery(SysUser.class,sql,userName);
        return userList != null && userList.size() > 0 ? userList.get(0) : null;
    }

    @Override
    public int updatePassword(SysUpdate update) {
        String sql = "update sys_user set userPwd = ? where userName = ?";
        int rows = baseUpdate(sql,update.getUpdatePwd(),update.getUserName());
        return rows;
    }

    @Override
    public int updateClass(SysUpdate update) {
        String sql = "update sys_user set userClass = ? where userName = ?";
        int rows = baseUpdate(sql,update.getUserClass(),update.getUserName());
        return rows;
    }

    @Override
    public List<SysContest> getEnrolledContest(SysUser user) {
        String sql = "select * from sys_contest where cid in (select cid from sys_enroll where uid = ?)";
        List<SysContest> contestList = baseQuery(SysContest.class,sql,user.getUid());
        return contestList;
    }
}
