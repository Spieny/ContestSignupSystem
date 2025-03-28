package com.ziahh.contest.controller;

import com.ziahh.contest.common.ContestStatus;
import com.ziahh.contest.common.Result;
import com.ziahh.contest.common.ResultCodeEnum;
import com.ziahh.contest.pojo.SysContest;
import com.ziahh.contest.pojo.SysEnroll;
import com.ziahh.contest.service.Impl.SysContestServiceImpl;
import com.ziahh.contest.service.SysContestService;
import com.ziahh.contest.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/contest/*")
public class SysContestController extends BaseController{

    private SysContestService contestService = new SysContestServiceImpl();

    protected void showAllContest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用服务层方法
        List<SysContest> allContest = contestService.findAllContest();
        //将比赛信息存入result,转换json发送给客户端
        Map<String,Object> data = new HashMap<>();
        data.put("itemList",allContest);
        WebUtil.writeJson(resp, Result.ok(data));
    }

    protected void enroll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = null;
        SysEnroll enroll = WebUtil.readJson(req,SysEnroll.class);
        System.out.println(enroll);

        //检查用户是否已经报名
        List<SysEnroll> enrollList = contestService.findSpecificUserEnroll(enroll);
        if(!enrollList.isEmpty()){
            result = Result.build(null, ResultCodeEnum.CONTEST_ENROLLED);
            WebUtil.writeJson(resp,result);
            return;
        }

        //检查比赛报名时间是否合法
        List<SysContest> contestList = contestService.findAllContest();
        SysContest sysContest = null;
            //获取比赛
        for(SysContest tempContest : contestList){
            System.out.println(tempContest);
            if(tempContest.getCid().equals(enroll.getCid())){
                sysContest = tempContest;
                break;
            }
        }
        Date contestBeginTime = sysContest.getContestBeginTime();
        Date contestEndTime = sysContest.getContestEndTime();
        
        //比赛报名时间已过
        if (contestEndTime.before(Date.valueOf(LocalDate.now()))){
            result = Result.build(null,ResultCodeEnum.CONTEST_ENROLLEND);
            WebUtil.writeJson(resp,result);
            return;
        }
        
        //比赛报名时间未到
        if (contestBeginTime.after(Date.valueOf(LocalDate.now()))){
            result = Result.build(null,ResultCodeEnum.CONTEST_ENROLLNOTSTART);
            WebUtil.writeJson(resp,result);
            return;
        }

        //比赛人数已满
        System.out.println("[debug]" + sysContest.getContestEnroll() + " >= " +  sysContest.getContestTotal());
        if(sysContest.getContestEnroll() >= sysContest.getContestTotal()){
            result = Result.build(null,ResultCodeEnum.CONTEST_FULL);
            WebUtil.writeJson(resp,result);
            return;
        }

        int rows = contestService.enroll(enroll);
        if (rows == 2){
            result = Result.ok(null);
        }
        WebUtil.writeJson(resp,result);

    }

    protected void checkEnroll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = null;
        SysEnroll enroll = WebUtil.readJson(req,SysEnroll.class);
        System.out.println(enroll.getCid() + " " + enroll.getUid());

        //检查用户是否已经报名
        List<SysEnroll> enrollList = contestService.findSpecificUserEnroll(enroll);
        if(!enrollList.isEmpty()){
            result = Result.build(null, ResultCodeEnum.CONTEST_ENROLLED);
            WebUtil.writeJson(resp,result);
        } else {
            result = Result.ok(null);
            WebUtil.writeJson(resp,result);
        }
    }

    protected void test(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
    protected void unenroll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = null;
        SysEnroll enroll = WebUtil.readJson(req,SysEnroll.class);
        System.out.println(enroll);

        //检查用户是否没有报名
        List<SysEnroll> enrollList = contestService.findSpecificUserEnroll(enroll);
        if(enrollList.isEmpty()){
            result = Result.build(null, ResultCodeEnum.CONTEST_NOTENROLLED);
            WebUtil.writeJson(resp,result);
            return;
        }

        //检查比赛报名时间是否合法
        List<SysContest> contestList = contestService.findAllContest();
        SysContest sysContest = null;
        //获取比赛
        for(SysContest tempContest : contestList){
            if(tempContest.getCid().equals(enroll.getCid())){
                sysContest = tempContest;
                break;
            }
        }
        Date contestBeginTime = sysContest.getContestBeginTime();
        Date contestEndTime = sysContest.getContestEndTime();

        //比赛报名时间已过
        if (contestEndTime.before(Date.valueOf(LocalDate.now()))){
            result = Result.build(null,ResultCodeEnum.CONTEST_ENROLLEND);
            WebUtil.writeJson(resp,result);
            return;
        }

        //比赛报名时间未到
        if (contestBeginTime.after(Date.valueOf(LocalDate.now()))){
            result = Result.build(null,ResultCodeEnum.CONTEST_ENROLLNOTSTART);
            WebUtil.writeJson(resp,result);
            return;
        }

        int rows = contestService.unenroll(enroll);
        if (rows == 2){
            result = Result.ok(null);
        }
        WebUtil.writeJson(resp,result);
        System.out.println("[ACTION] 用户" + enroll.getUid() + " 取消报名比赛 " + enroll.getCid());
    }

    protected void addContest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        SysContest contest = WebUtil.readJson(req,SysContest.class);
        //检验时间是否合法
        if (contest.getContestBeginTime().after(contest.getContestEndTime())){
            WebUtil.writeJson(resp,Result.build(null,ResultCodeEnum.CONTEST_TIME_ILLEGAL));
            return;
        }
        //根据比赛报名时间设置STATUS
        if (contest.getContestBeginTime().after(new Date( System.currentTimeMillis()))){
            contest.setContestStatus(ContestStatus.NOT_START.getCode());
        } else if (contest.getContestEndTime().after(new Date(System.currentTimeMillis()))){
            contest.setContestStatus(ContestStatus.STARTED.getCode());
        } else {
            contest.setContestStatus(ContestStatus.END.getCode());
        }

        int rows = contestService.addContest(contest);
        if(rows > 0){
            WebUtil.writeJson(resp,Result.ok(null));
        }
    }

    protected void findContest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String cid = req.getParameter("cid");
        SysContest contest = contestService.findContestByCid(cid);
        if(contest != null){
            WebUtil.writeJson(resp,Result.ok(contest));
        } else {
            WebUtil.writeJson(resp,Result.build(null,ResultCodeEnum.CONTEST_NOTFOUND));
        }
    }

    protected void updateContest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        SysContest contest = WebUtil.readJson(req,SysContest.class);
        System.out.println("GET：" + contest);
        //检验时间是否合法
        if (contest.getContestBeginTime().after(contest.getContestEndTime())){
            WebUtil.writeJson(resp,Result.build(null,ResultCodeEnum.CONTEST_TIME_ILLEGAL));
            return;
        }
        //根据比赛报名时间设置STATUS
        if (contest.getContestBeginTime().after(new Date( System.currentTimeMillis()))){
            contest.setContestStatus(ContestStatus.NOT_START.getCode());
        } else if (contest.getContestEndTime().after(new Date(System.currentTimeMillis()))){
            contest.setContestStatus(ContestStatus.STARTED.getCode());
        } else {
            contest.setContestStatus(ContestStatus.END.getCode());
        }

        int rows = contestService.updateContest(contest);
        System.out.println("r:" + rows);
        if(rows > 0){
            WebUtil.writeJson(resp,Result.ok(null));
        } else {
            WebUtil.writeJson(resp,Result.build(null,ResultCodeEnum.NOT_CHANGED));
        }
    }
}
