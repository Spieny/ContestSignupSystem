package com.ziahh.contest.service;

import com.ziahh.contest.pojo.SysContest;
import com.ziahh.contest.pojo.SysEnroll;
import com.ziahh.contest.pojo.SysUser;

import java.util.List;

public interface SysContestService {

    /**
     * 用户报名一个比赛
     * @param enroll 报名记录
     * @return 返回大于0的整数代表成功
     */
    int enroll(SysEnroll enroll);

    /**
     * 用户取消报名
     * @param enroll 包含cid和uid的报名信息
     * @return 返回大于0的整数代表成功
     */
    int unenroll(SysEnroll enroll);

    /**
     * 获取所有比赛
     * @return
     */
    List<SysContest> findAllContest();

    /**
     * 获取装有一条报名记录的List
     * @return 如果为空，说明用户没有报名
     */
    List<SysEnroll> findSpecificUserEnroll(SysEnroll enroll);
}
