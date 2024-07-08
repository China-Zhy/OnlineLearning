package nxu.mapper;

import nxu.entity.Notice;

import java.util.List;
import java.util.Map;

/**
 * 公告数据层接口(唐馨源)
 */
public interface NoticeMapper {

    /**
     * 插入公告
     *
     * @param map 条件参数
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
     * 根据系统、课程号查询公告
     *
     * @param target 系统、课程号
     * @return 公告列表
     */

    List<Notice> getNoticeByTarget(int target);

    /**
     * 根据用户id查询公告
     *
     * @param userId 用户id
     * @return 公告列表
     */
    List<Notice> getNoticeByUserId(int userId);

    /**
     * 更新公告
     *
     * @param map 条件参数
     * @return 更新成功返回1，否则返回0
     */
    int updateNotice(Map<String, Object> map);

}
