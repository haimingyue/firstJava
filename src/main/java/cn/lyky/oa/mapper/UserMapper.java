package cn.lyky.oa.mapper;

import cn.lyky.oa.entity.User;
import cn.lyky.oa.utils.MybatisUtils;

public class UserMapper {
    public User selectByUsername(String username) {
        User user = (User)MybatisUtils.executeQuery(sqlSession -> sqlSession.selectOne("usermapper.selectByUsername", username));
        return user;
    }
}
