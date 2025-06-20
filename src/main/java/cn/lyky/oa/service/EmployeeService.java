package cn.lyky.oa.service;

import cn.lyky.oa.entity.Employee;
import cn.lyky.oa.mapper.EmployeeMapper;
import cn.lyky.oa.utils.MybatisUtils;

public class EmployeeService {
    public Employee selectById(Long employeeId){
        Employee employee = (Employee)MybatisUtils.executeQuery(sqlSession -> {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            return mapper.selectById(employeeId);
        });
        return employee;
    }
}
