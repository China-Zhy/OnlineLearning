package nxu.service;

import nxu.entity.Message;
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
     * @param map 条件参数
     * @return 插入成功返回1，否则返回0
     */
    @Override
    public int insertMessage(Map<String, Object> map) {
        return MybatisUtil.getSqlSession().getMapper(nxu.mapper.MessageMapper.class).insertMessage(map);
    }

    /**
     * 显示消息内容
     *
     * @param map 条件参数
     * @return 返回符合条件的消息列表
     */
    @Override
    public List<Message> getMessages(Map<String, Object> map) {
        return MybatisUtil.getSqlSession().getMapper(nxu.mapper.MessageMapper.class).getMessages(map);
    }
}