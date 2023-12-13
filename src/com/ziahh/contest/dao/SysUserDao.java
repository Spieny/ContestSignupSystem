package com.ziahh.contest.dao;

import com.ziahh.contest.pojo.SysUser;

public interface SysUserDao {

    /**
     * 注册一个新用户
     * @param sysUser 以userName和userPwd为字段的SysUser实体类对象
     * @return 返回正整数代表成功
     */
    int register(SysUser sysUser);

    /**
     * 查询一个用户
     * @param userName 用户名
     * @return 返回一个SysUser,不为null则该用户存在且密码正确
     */
    SysUser findUser(String userName);
}
