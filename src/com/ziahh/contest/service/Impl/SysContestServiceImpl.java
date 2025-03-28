package com.ziahh.contest.service.Impl;

import com.ziahh.contest.common.ContestStatus;
import com.ziahh.contest.dao.SysContestDao;
import com.ziahh.contest.dao.impl.SysContestDaoImpl;
import com.ziahh.contest.pojo.SysContest;
import com.ziahh.contest.pojo.SysEnroll;
import com.ziahh.contest.pojo.SysUser;
import com.ziahh.contest.service.SysContestService;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public class SysContestServiceImpl implements SysContestService {

    private SysContestDao contestDao = new SysContestDaoImpl();

    @Override
    public int enroll(SysEnroll enroll) {
        int rows = contestDao.enroll(enroll);
        return rows;
    }

    @Override
    public int unenroll(SysEnroll enroll) {
        int rows = contestDao.unenroll(enroll);
        return rows;
    }

    @Override
    public List<SysContest> findAllContest() {
        return contestDao.findAllContest();
    }

    @Override
    public List<SysEnroll> findSpecificUserEnroll(SysEnroll enroll) {
        return contestDao.findSpecificUserEnroll(enroll.getCid(),enroll.getUid());
    }

    @Override
    public boolean checkContestFull(SysEnroll enroll) {
        return false;
    }

    @Override
    public int addContest(SysContest contest) {
        int rows = contestDao.addContest(contest);
        return rows;
    }

    @Override
    public SysContest findContestByCid(String cid) {
        return contestDao.findContestByCid(cid);
    }

    @Override
    public int updateContest(SysContest contest) {
        return contestDao.updateContest(contest);
    }
}
