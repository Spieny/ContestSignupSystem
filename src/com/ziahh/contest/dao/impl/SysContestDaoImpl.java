package com.ziahh.contest.dao.impl;

import com.ziahh.contest.dao.BaseDao;
import com.ziahh.contest.dao.SysContestDao;
import com.ziahh.contest.pojo.SysContest;
import com.ziahh.contest.pojo.SysEnroll;

import java.util.List;

public class SysContestDaoImpl extends BaseDao implements SysContestDao {

    @Override
    public List<SysContest> findAllContest() {
        String sql = "select * from sys_contest";
        List<SysContest> contests = baseQuery(SysContest.class,sql);
        return contests;
    }

    @Override
    public int enroll(SysEnroll enroll) {
        String sql = "insert into sys_enroll values(DEFAULT,?,?)";
        //更新报名人数
        String updateSql = "update sys_contest set contestEnroll = contestEnroll + 1 where cid = ?";
        int rows = baseUpdate(sql,enroll.getCid(), enroll.getUid());
        int rows2 = baseUpdate(updateSql,enroll.getCid());
        return rows + rows2;
    }

    @Override
    public List<SysEnroll> findUserEnrolls(Integer uid) {
        String sql = "select * from sys_enroll where uid = ?";
        List<SysEnroll> list = baseQuery(SysEnroll.class,sql,uid);
        return list;
    }

    @Override
    public List<SysEnroll> findSpecificUserEnroll(Integer cid, Integer uid) {
        String sql = "select * from sys_enroll where cid = ? and uid = ?";
        List<SysEnroll> list = baseQuery(SysEnroll.class,sql,cid,uid);
        return list;
    }

    @Override
    public int unenroll(SysEnroll enroll) {
        String sql = "delete from sys_enroll where cid = ? and uid = ?";
        String sql2 = "update sys_contest set contestEnroll = contestEnroll - 1 where cid = ?";
        int rows1 = baseUpdate(sql,enroll.getCid(),enroll.getUid());
        int rows2 = baseUpdate(sql2,enroll.getCid());
        return rows1 + rows2;
    }


}
