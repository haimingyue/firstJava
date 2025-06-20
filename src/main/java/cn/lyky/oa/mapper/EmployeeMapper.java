package cn.lyky.oa.mapper;

import cn.lyky.oa.entity.Employee;

public interface EmployeeMapper {
    public Employee selectById(Long employeeId);
}
