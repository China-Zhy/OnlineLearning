package nxu.service;

import nxu.entity.Message;

import java.util.List;
import java.util.Map;

/**
 * 消息服务层接口 (唐馨源)
 */
public interface MessageService {

    /**
     * 插入消息
     *
     * @param map 条件参数
     * @return 插入成功返回1，否则返回0
     */
    int insertMessage(Map<String, Object> map);

    /**
     * 显示消息内容
     *
     * @param map 条件参数
     * @return 返回符合条件的消息列表
     */
    List<Message> getMessages(Map<String, Object> map);
}