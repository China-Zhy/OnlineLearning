import com.github.pagehelper.PageInfo;
import nxu.entity.Message;
import nxu.service.MessageService;
import nxu.service.impl.MessageServiceImpl;
import org.junit.Test;

import java.util.HashMap;

/**
 * 消息相关功能测试 (唐馨源)
 */
public class MessageTest {

    private static final MessageService messageService = new MessageServiceImpl();

    @Test
    public void test() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("sender", 1);
        map.put("accept", 2);
        map.put("info", " hello");
        int i = messageService.insertMessage(map);
        System.out.println(i);
    }

    @Test
    public void test2() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("sender", 3);
        map.put("accept", 4);
        map.put("pageIndex", 1);
        map.put("pageSize", 2);
        PageInfo<Message> message = messageService.getMessages(map);
        for (Message m : message.getList()) {
            System.out.println(m);
        }
    }
}