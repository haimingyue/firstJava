package cn.lyky.oa.mapper;

import cn.lyky.oa.entity.Employee;
import cn.lyky.oa.utils.MybatisUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeMapperTest {
    @Test
    public void selectById() {
        Employee emp = (Employee) MybatisUtils.executeQuery(sqlSession -> {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.selectById(4l);
            System.out.println(employee);
            return employee;
        });
    }
}