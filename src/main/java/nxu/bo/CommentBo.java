package nxu.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nxu.entity.Comment;
import nxu.entity.User;

/**
 * 评论信息页数据业务实体类 (张宏业)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentBo {
    private User user;          // 评论用户
    private Comment comment;    // 评论内容
}