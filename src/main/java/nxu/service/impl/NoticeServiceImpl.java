package nxu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import nxu.entity.Notice;
import nxu.mapper.NoticeMapper;
import nxu.service.NoticeService;
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
     * @param map 条件参数(传入数据：title 公告标题,info 公告信息 ,dateline 截止时间, target 系统0或课程号 ,user_id 用户)
     * @return 插入成功返回1，否则返回0
     */
    @Override
    public int insertNotice(Map<String, Object> map) {
        int i = MybatisUtil.getSqlSession().getMapper(NoticeMapper.class).insertNotice(map);
        MybatisUtil.getSqlSession().close();
        return i;
    }

    /**
     * 删除公告
     *
     * @param id 公告编号
     * @return 删除成功返回1，否则返回0
     */
    @Override
    public int deleteNotice(int id) {
        int i = MybatisUtil.getSqlSession().getMapper(NoticeMapper.class).deleteNotice(id);
        MybatisUtil.getSqlSession().close();
        return i;
    }

    /**
     * 分页查询所有公告
     *
     * @return 公告列表
     */
    @Override
    public PageInfo<Notice> getNotice(Map<String, Object> map) {
        PageHelper.startPage((int) map.get("pageIndex"), (int) map.get("pageSize"));
        List<Notice> notice = MybatisUtil.getSqlSession().getMapper(NoticeMapper.class).getNotice(map);
        MybatisUtil.getSqlSession().close();
        return new PageInfo<>(notice);
    }

    /**
     * 查询一个公告
     *
     * @param id 公告编号
     * @return 公告
     */
    @Override
    public Notice getOneNotice(int id) {
        Notice notice = MybatisUtil.getSqlSession().getMapper(NoticeMapper.class).getOneNotice(id);
        MybatisUtil.getSqlSession().close();
        return notice;
    }

    /**
     * 更新公告
     *
     * @param map 条件参数(title 公告标题, info 公告消息, dateline 截止时间, target 系统0或课程号)
     * @return 更新成功返回1，否则返回0
     */
    @Override
    public int updateNotice(Map<String, Object> map) {
        int i = MybatisUtil.getSqlSession().getMapper(NoticeMapper.class).updateNotice(map);
        MybatisUtil.getSqlSession().close();
        return i;
    }
}