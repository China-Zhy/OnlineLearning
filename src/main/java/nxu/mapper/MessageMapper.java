package nxu.mapper;

import nxu.entity.Message;

import java.util.List;
import java.util.Map;

/**
 * 消息发送数据层接口(唐馨源)
 */
public interface MessageMapper {

    /**
     * 插入消息
     *
     * @param map 条件参数(sender 发送者,accept 接收者,info 发送消息)
     * @return 插入成功返回1，否则返回0
     */
    int insertMessage(Map<String, Object> map);

    /**
     * 显示消息内容
     *
     * @param map 条件参数(sender 发送者,accept 接收者 ,pageIndex 页码,pageSize 每页大小)
     * @return 返回符合条件的消息列表
     */
    List<Message> getMessages(Map<String, Object> map);

}