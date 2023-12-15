package com.ziahh.contest.controller;

import com.ziahh.contest.common.Result;
import com.ziahh.contest.common.ResultCodeEnum;
import com.ziahh.contest.pojo.SysUser;
import com.ziahh.contest.service.Impl.SysUserServiceImpl;
import com.ziahh.contest.service.SysUserService;
import com.ziahh.contest.util.MD5Util;
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
        SysUser loginUser = WebUtil.readJson(req,SysUser.class);
        String userName = loginUser.getUserName();
        String userPwd = loginUser.getUserPwd();
        SysUser user = userService.findByUsername(userName);
        Result result = null;
        if(user != null){
            if(user.getUserPwd().equals(MD5Util.encrypt(userPwd))){
                //抹去用户密码，防止数据泄露
                user.setUserPwd("");
                result = Result.ok(user);
            } else {
                result = Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
            }
        } else {
            result = Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }
        // 将result对象转换为JSON串响应给客户端
        WebUtil.writeJson(resp,result);
    }

    /**
     * 接受前端验证用户名是否使用的请求
     */
    protected void checkUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String userPwd = req.getParameter("userPwd");

        //尝试使用用户名获取用户
        SysUser user = userService.findByUsername(userName);
        Result result = null;
        if(null != user){
            result=Result.build(null, ResultCodeEnum.USERNAME_USED);
        } else {
            result = Result.ok(null);
        }
        // 将result对象转换为JSON串响应给客户端
        WebUtil.writeJson(resp,result);
    }

    /**
     * 接受前端注册的要求
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SysUser newUser = WebUtil.readJson(req,SysUser.class);
        int rows = userService.register(newUser);
        Result result = null;
        if (rows > 0){
            result = Result.ok(null);
        } else {
            result = Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp,result);
    }
}
