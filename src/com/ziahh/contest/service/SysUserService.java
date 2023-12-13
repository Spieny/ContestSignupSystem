package com.ziahh.contest.service;

import com.ziahh.contest.pojo.SysUser;

public interface SysUserService {

    /**
     * 向数据库中增加用户的方法
     * @param sysUser 用户名与明文密码为对象的SysUser对象
     * @return 注册成功返回1 失败返回0
     */
    int register(SysUser sysUser);

    /**
     * 请求登录，根据username向数据库查询用户
     * @param username 用户名与明文密码为对象的SysUser对象
     * @return
     */
    SysUser findByUsername(String username);
}
