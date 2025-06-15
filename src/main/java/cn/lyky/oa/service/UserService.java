package cn.lyky.oa.service;

import cn.lyky.oa.entity.User;
import cn.lyky.oa.mapper.UserMapper;
import cn.lyky.oa.service.exception.LoginException;
import cn.lyky.oa.utils.Md5Utils;

public class UserService  {
    private UserMapper userMapper = new UserMapper();

    public User checkLogin(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
//            return new Exception()
            throw new LoginException("用户名不存在");
        }
        String md5 = Md5Utils.md5Digest(password, user.getSalt());
        if (!md5.equals(user.getPassword())) {
            throw new LoginException("密码错误");
        }
        return user;
//        return '';
    }
}
