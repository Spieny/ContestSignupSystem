package com.ziahh.contest.dao;

import com.ziahh.contest.pojo.SysContest;
import com.ziahh.contest.pojo.SysEnroll;

import java.util.List;

public interface SysContestDao {

    /**
     * 向数据库获取所有比赛
     * @return 装有所有比赛的List
     */
    List<SysContest> findAllContest();

    /**
     * 向数据库上传一条报名记录
     * @return 返回大于0的整数表示成功，否则失败
     */
    int enroll(SysEnroll enroll);

    /**
     * 向数据库获取一个用户的所有报名记录
     * @return
     */
    List<SysEnroll> findUserEnrolls(Integer uid);

    /**
     * 向数据库获取用户的一条指定的报名记录
     */
    List<SysEnroll> findSpecificUserEnroll(Integer cid,Integer uid);

    /**
     * 向数据库删除一条报名记录
     * @param enroll
     * @return
     */
    int unenroll(SysEnroll enroll);
}
