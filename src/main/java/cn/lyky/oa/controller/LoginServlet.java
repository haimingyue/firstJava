package cn.lyky.oa.controller;

import cn.lyky.oa.entity.User;
import cn.lyky.oa.service.UserService;
import cn.lyky.oa.utils.ResponseUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        // 接受用户数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        ResponseUtils responseData = null;
        try {
            User user = userService.checkLogin(username, password);
            responseData = new ResponseUtils().put("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            responseData = new ResponseUtils().put(e.getClass().getSimpleName(), e.getMessage());
        }
        // 调用业务逻辑
        // 返回JSON结果
        resp.getWriter().println(responseData.toJsonString());
    }
}
