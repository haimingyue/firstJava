package cn.lyky.oa.service;

import cn.lyky.oa.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService = new UserService();
    @Test
    public void checkLogin1() {
        User user = userService.checkLogin("test", "test");
        System.out.println(user);
    }

    @Test
    public void checkLogin2() {
        User user = userService.checkLogin("test1", "test");
        System.out.println(user);
    }
}