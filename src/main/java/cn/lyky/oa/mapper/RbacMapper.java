package cn.lyky.oa.mapper;

import cn.lyky.oa.entity.Node;
import cn.lyky.oa.utils.MybatisUtils;

import java.util.List;

public class RbacMapper {
    public List<Node> selectNodeByUserId(Long userId) {
        List<Node> list = (List) MybatisUtils.executeQuery(sqlSession -> sqlSession.selectList("rbacmapper.selectNodeByUserId", userId));
        return list;
    }
}
