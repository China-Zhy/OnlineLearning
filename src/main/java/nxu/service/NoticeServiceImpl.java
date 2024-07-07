package nxu.service;

import nxu.entity.Notice;
import nxu.mapper.NoticeMapper;
import nxu.utils.MybatisUtil;

import java.util.List;
import java.util.Map;

/**
 * 公告服务层接口实现 (唐馨源)
 */
public class NoticeServiceImpl implements NoticeService {

    /**
     * 插入公告
     *
     * @param map 条件参数
     * @return 插入成功返回1，否则返回0
     */
    @Override
    public int insertNotice(Map<String, Object> map) {
        return MybatisUtil.getSqlSession().getMapper(NoticeMapper.class).insertNotice(map);
    }

    /**
     * 删除公告
     *
     * @param id 公告编号
     * @return 删除成功返回1，否则返回0
     */
    @Override
    public int deleteNotice(int id) {
        return MybatisUtil.getSqlSession().getMapper(NoticeMapper.class).deleteNotice(id);
    }

    /**
     * 根据系统、课程号查询公告
     *
     * @param target 系统、课程号
     * @return 公告列表
     */
    @Override
    public List<Notice> getNoticeByTarget(int target) {
        return MybatisUtil.getSqlSession().getMapper(NoticeMapper.class).getNoticeByTarget(target);
    }

    /**
     * 根据用户id查询公告
     *
     * @param userId 用户id
     * @return 公告列表
     */
    @Override
    public List<Notice> getNoticeByUserId(int userId) {
        return MybatisUtil.getSqlSession().getMapper(NoticeMapper.class).getNoticeByUserId(userId);
    }

    /**
     * 更新公告
     *
     * @param map 条件参数
     * @return 更新成功返回1，否则返回0
     */
    @Override
    public int updateNotice(Map<String, Object> map) {
        return MybatisUtil.getSqlSession().getMapper(NoticeMapper.class).updateNotice(map);
    }
}