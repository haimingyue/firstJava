package cn.lyky.oa.service;

import cn.lyky.oa.entity.Notice;
import cn.lyky.oa.mapper.NoticeMapper;
import cn.lyky.oa.utils.MybatisUtils;

import java.util.List;

public class NoticeService {
    public List<Notice> getNoticeList(Long receiverId){
        return (List)MybatisUtils.executeQuery(sqlSession -> {
            NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
            return mapper.selectByReceiverId(receiverId);
        });
    }
}
