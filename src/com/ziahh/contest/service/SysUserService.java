package com.ziahh.contest.service;

import com.ziahh.contest.pojo.SysContest;
import com.ziahh.contest.pojo.SysUpdate;
import com.ziahh.contest.pojo.SysUser;

import java.util.List;

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

    /**
     * 更新用户数据
     * @param update 更新实体
     * @return
     */
    int updateClass(SysUpdate update);

    /**
     * 更新用户密码数据
     * @param update 更新实体，密码为明文
     * @return
     */
    int updatePassword(SysUpdate update);

    /**
     * 获取一个用户已经报名的比赛
     * @param user
     * @return
     */
    List<SysContest> findEnrolledContest(SysUser user);
}
