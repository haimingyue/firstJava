package cn.lyky.oa.service;

import cn.lyky.oa.entity.Department;
import cn.lyky.oa.mapper.DepartmentMapper;
import cn.lyky.oa.utils.MybatisUtils;

public class DepartmentService {
    public Department selectById(Long departmentId){
        return (Department)MybatisUtils.executeQuery(sqlSession -> sqlSession.getMapper(DepartmentMapper.class).selectById(departmentId));
    }
}
