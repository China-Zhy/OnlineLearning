import nxu.entity.Comment;
import nxu.service.CommentService;
import nxu.service.CommentServiceImpl;
import org.junit.Test;

/**
 * 评论相关功能测试 (唐馨源)
 */
public class CommentTest {
    CommentService commentService = new CommentServiceImpl();

    @Test
    public void Test1() {
        int i = commentService.insertComment(1, 1, 5, "good");
        System.out.println(i);
    }

    @Test
    public void Test2() {
        int i = commentService.updateCommentGood(1);
        System.out.println(i);
    }

    @Test
    public void Test3() {
        int i = commentService.deleteComment(3);
        System.out.println(i);
    }

    @Test
    public void Test4() {
        Comment comment = commentService.getComment(1);
        System.out.println(comment);
    }

}
