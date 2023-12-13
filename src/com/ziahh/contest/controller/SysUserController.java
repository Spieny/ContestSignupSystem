package com.ziahh.contest.controller;

import com.ziahh.contest.common.Result;
import com.ziahh.contest.common.ResultCodeEnum;
import com.ziahh.contest.pojo.SysUser;
import com.ziahh.contest.service.Impl.SysUserServiceImpl;
import com.ziahh.contest.service.SysUserService;
import com.ziahh.contest.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/user/*")
public class SysUserController extends BaseController{

    private SysUserService userService = new SysUserServiceImpl();

    /**
     * 接受前端登录账号的请求
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String userPwd = req.getParameter("userPwd");
        SysUser user = userService.findByUsername(userName);
    }

    /**
     * 接受前端验证用户名是否使用的请求
     */
    protected void checkUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String userPwd = req.getParameter("userPwd");
        System.out.println("checkusername");
        //尝试使用用户名获取用户
        SysUser user = userService.findByUsername(userName);
        Result result = null;
        if(null != user){
            result=Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        // 将result对象转换为JSON串响应给客户端
        WebUtil.writeJson(resp,result);
    }

}
