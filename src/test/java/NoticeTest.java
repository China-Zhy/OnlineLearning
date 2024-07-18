import com.github.pagehelper.PageInfo;
import nxu.entity.Notice;
import nxu.service.NoticeService;
import nxu.service.impl.NoticeServiceImpl;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

/**
 * 公告相关功能测试 (唐馨源)
 */
public class NoticeTest {

    private static final NoticeService noticeService = new NoticeServiceImpl();

    @Test
    public void test1() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("title", "title");
        map.put("info", "info");
        Date date = new Date("2024/12/12 11:06:10");
        map.put("dateline", date);
        map.put("target", 0);
        map.put("userId", 1);
        int i = noticeService.insertNotice(map);
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = noticeService.deleteNotice(2);
        System.out.println(i);
    }

    @Test
    public void test6() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageIndex", 1);
        map.put("pageSize", 2);
        map.put("target", 9);
        PageInfo<Notice> notices = noticeService.getNotice(map);
        for (Notice notice : notices.getList()) {
            System.out.println(notice);
        }
    }

    @Test
    public void test3() {
        Notice oneNotice = noticeService.getOneNotice(1);
        System.out.println(oneNotice);
    }

    @Test
    public void test5() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("title", "title111");
        map.put("info", "info111");
        Date date = new Date("2024/10/12 11:06:10");
        map.put("dateline", date);
        map.put("target", 1);
        map.put("userId", 1);
        int i = noticeService.updateNotice(map);
        System.out.println(i);
    }
}