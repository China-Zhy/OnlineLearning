import nxu.entity.Message;
import nxu.service.MessageService;
import nxu.service.MessageServiceImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * 消息相关功能测试 (唐馨源)
 */
public class MessageTest {
    MessageService messageService = new MessageServiceImpl();

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
        List<Message> message = messageService.getMessages(map);
        for (Message m : message) {
            System.out.println(m);
        }
    }
}