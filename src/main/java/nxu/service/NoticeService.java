package nxu.service;

import nxu.entity.Notice;

import java.util.List;
import java.util.Map;

/**
 * 公告服务层接口 (唐馨源)
 */
public interface NoticeService {

    /**
     * 插入公告
     *
     * @param map 条件参数(传入数据：title 公告标题,info 公告信息 ,dateline 截止时间, target 系统0或课程号 ,user_id 用户)
     * @return 插入成功返回1，否则返回0
     */
    int insertNotice(Map<String, Object> map);

    /**
     * 删除公告
     *
     * @param id 公告编号
     * @return 删除成功返回1，否则返回0
     */
    int deleteNotice(int id);

    /**
     * 查询所有公告
     *
     * @return 公告列表
     */
    List<Notice> getNotice(Map<String, Object> map);
    /**
     * 查询一个公告
     *
     * @param id 公告编号
     * @return 公告
     */
    Notice getOneNotice(int id);


    /**
     * 更新公告
     *
     * @param map 条件参数(title 公告标题, info 公告消息, dateline 截止时间, target 系统0或课程号)
     * @return 更新成功返回1，否则返回0
     */
    int updateNotice(Map<String, Object> map);
}