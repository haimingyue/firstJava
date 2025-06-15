package cn.lyky.oa.controller;

import cn.lyky.oa.entity.User;
import cn.lyky.oa.service.UserService;
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
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            User user = userService.checkLogin(username, password);
            //处理结果编码,0代表处理成功,非0代表处理失败
            result.put("code", "0");
            result.put("message", "success");
            Map data = new LinkedHashMap();
            data.put("user", user);
            result.put("data", data);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", e.getClass().getSimpleName());
            result.put("msg", e.getMessage());
        }
        // 调用业务逻辑
        // 返回JSON结果
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = objectMapper.writeValueAsString(result);
        resp.getWriter().println(json);
    }
}
