package cn.lyky.oa.mapper;

import cn.lyky.oa.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    public Employee selectById(Long employeeId);
    public List<Employee> selectByParams(Map<String, Object> params);
}
