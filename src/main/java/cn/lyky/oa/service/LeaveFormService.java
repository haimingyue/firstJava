package cn.lyky.oa.service;

import cn.lyky.oa.entity.Employee;
import cn.lyky.oa.entity.LeaveForm;
import cn.lyky.oa.entity.ProcessFlow;
import cn.lyky.oa.mapper.EmployeeMapper;
import cn.lyky.oa.mapper.LeaveFormMapper;
import cn.lyky.oa.mapper.ProcessFlowMapper;
import cn.lyky.oa.utils.MybatisUtils;

import java.util.Date;

public class LeaveFormService {
    EmployeeService employeeService = new EmployeeService();
    public LeaveForm createLeaveForm(LeaveForm form) {
        //1.持久化form表单数据,8级以下员工表单状态为processing,8级(总经理)状态为approved
        LeaveForm f = (LeaveForm) MybatisUtils.executeUpdate(sqlSession -> {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.selectById(form.getEmployeeId());
            if (employee.getLevel() == 8) {
                form.setState("approved");
            } else {
                form.setState("process");
            }
            LeaveFormMapper leaveFormMapper = sqlSession.getMapper(LeaveFormMapper.class);
            leaveFormMapper.insert(form);

            ProcessFlowMapper processFlowMapper = sqlSession.getMapper(ProcessFlowMapper.class);
            ProcessFlow flow1 = new ProcessFlow();
            flow1.setFormId(form.getFormId());
            flow1.setOperatorId(employee.getEmployeeId());
            flow1.setAction("apply");
            flow1.setCreateTime(new Date());
            flow1.setOrderNo(1);
            flow1.setState("complete");
            flow1.setIsLast(0);
            processFlowMapper.insert(flow1);

            if (employee.getLevel() < 7) {
                Employee dmanager = employeeService.selectLeader(employee.getEmployeeId());
                ProcessFlow flow2 = new ProcessFlow();
                flow2.setFormId(form.getFormId());
                flow2.setOperatorId(dmanager.getEmployeeId());
                flow2.setAction("audit");
                flow2.setCreateTime(new Date());
                flow2.setOrderNo(2);
                flow2.setState("process");
                long diff = form.getEndTime().getTime() - form.getStartTime().getTime();
                if(diff / (1000 * 60 * 60) * 1f >= 72) {
                    flow2.setIsLast(0);
                    processFlowMapper.insert(flow2);
                    Employee manager = employeeService.selectLeader(dmanager.getEmployeeId());
                    ProcessFlow flow3 = new ProcessFlow();
                    flow3.setFormId(form.getFormId());
                    flow3.setOperatorId(manager.getEmployeeId());
                    flow3.setAction("audit");
                    flow3.setCreateTime(new Date());
                    flow3.setState("ready");
                    flow3.setOrderNo(3);
                    flow3.setIsLast(1);
                    processFlowMapper.insert(flow3);
                } else {
                    flow2.setIsLast(1);
                    processFlowMapper.insert(flow2);
                }
            } else if (employee.getLevel() == 7) {
                Employee manager = employeeService.selectLeader(employee.getEmployeeId());
                ProcessFlow flow2 = new ProcessFlow();
                flow2.setFormId(form.getFormId());
                flow2.setOperatorId(manager.getEmployeeId());
                flow2.setAction("audit");
                flow2.setCreateTime(new Date());
                flow2.setState("process");
                flow2.setOrderNo(2);
                flow2.setIsLast(1);
                processFlowMapper.insert(flow2);
            } else if (employee.getLevel() == 8) {
                ProcessFlow flow2 = new ProcessFlow();
                flow2.setFormId(form.getFormId());
                flow2.setOperatorId(employee.getEmployeeId());
                flow2.setAction("audit");
                flow2.setResult("approved");
//                flow2.setOrderNo(1);
                flow2.setReason("自动通过");
                flow2.setAuditTime(new Date());
                flow2.setCreateTime(new Date());
                flow2.setOrderNo(2);
                flow2.setIsLast(1);
                flow2.setState("complete");
                processFlowMapper.insert(flow2);
            }


            return form;
        });
        return f;
    };
}
