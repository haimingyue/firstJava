package cn.lyky.oa.service;

import cn.lyky.oa.entity.Node;
import cn.lyky.oa.mapper.RbacMapper;

import java.util.List;

public class RbacService {
    private RbacMapper rbacMapper = new RbacMapper();
    public List<Node> selectNodeByUserId (Long userId) {
        return rbacMapper.selectNodeByUserId(userId);
    }
}
