package nxu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import nxu.entity.Message;

import nxu.service.MessageService;
import nxu.utils.MybatisUtil;

import java.util.List;
import java.util.Map;

/**
 * 评论服务层实现类 (唐馨源)
 */
public class MessageServiceImpl implements MessageService {

    /**
     * 插入消息
     *
     * @param map 条件参数(sender 发送者,accept 接收者,info 发送消息)
     * @return 插入成功返回1，否则返回0
     */
    @Override
    public int insertMessage(Map<String, Object> map) {
        return MybatisUtil.getSqlSession().getMapper(nxu.mapper.MessageMapper.class).insertMessage(map);
    }


    /**
     * 查询消息
     *
     * @param map (sender 发送者,accept 接收者 ,pageIndex 页码,pageSize 每页大小)
     * @return 返回分页消息列表
     */
    @Override
    public PageInfo<Message> getMessages(Map<String, Object> map) {
        PageHelper.startPage((int) map.get("pageIndex"), (int) map.get("pageSize"));
        List<Message> messages = MybatisUtil.getSqlSession().getMapper(nxu.mapper.MessageMapper.class).getMessages(map);
        return new PageInfo<>(messages);
    }


}