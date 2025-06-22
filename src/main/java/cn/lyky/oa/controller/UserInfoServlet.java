package cn.lyky.oa.controller;

import cn.lyky.oa.entity.Department;
import cn.lyky.oa.entity.Employee;
import cn.lyky.oa.entity.Node;
import cn.lyky.oa.service.DepartmentService;
import cn.lyky.oa.service.EmployeeService;
import cn.lyky.oa.service.RbacService;
import cn.lyky.oa.utils.ResponseUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/user_info")
public class UserInfoServlet extends HttpServlet {
    private RbacService rbacService = new RbacService();
    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        String eid = request.getParameter("eid");
        List<Node> nodes = rbacService.selectNodeByUserId(Long.parseLong(uid));
        List<Map> treeList = new ArrayList<>();
        Map module = null;
        for(Node node : nodes){
            if(node.getNodeType() == 1){
                module = new LinkedHashMap();
                module.put("node", node);
                module.put("children", new ArrayList());
                treeList.add(module);
            }else if(node.getNodeType() == 2){
                List children = (List)module.get("children");
                children.add(node);
            }
        }
        System.out.println("eid --->" + eid);
        Employee employee = employeeService.selectById(Long.parseLong(eid));
        Department department = departmentService.selectById(Long.parseLong(eid));
        String json = new ResponseUtils().put("nodeList", treeList).put("employee", employee).put("department", department).toJsonString();


        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
