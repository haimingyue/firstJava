package cn.lyky.oa.service;

import cn.lyky.oa.entity.Node;
import org.junit.Test;

import java.util.List;

public class RbacServiceTest {
    private RbacService rbacService = new RbacService();
    @Test
    public void selectNodeByUserId() {
        List<Node> nodes =  rbacService.selectNodeByUserId(3l);
        for (Node node : nodes) {
            System.out.println(node.getNodeName());
        }
    }
}