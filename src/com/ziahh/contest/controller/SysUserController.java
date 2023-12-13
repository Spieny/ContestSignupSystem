package com.ziahh.contest.controller;

import com.ziahh.contest.pojo.SysUser;
import com.ziahh.contest.service.Impl.SysUserServiceImpl;
import com.ziahh.contest.service.SysUserService;
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
}
