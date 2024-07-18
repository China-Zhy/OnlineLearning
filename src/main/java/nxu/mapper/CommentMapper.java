package nxu.mapper;

import nxu.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 评论数据层接口 (唐馨源)
 */
public interface CommentMapper {
    /**
     * 插入评论
     *
     * @param courseId 评论课程编号
     * @param userId   评论用户编号
     * @param score    评论星级
     * @param info     评论内容
     * @return 插入成功返回1，否则返回0
     */
    int insertComment(@Param("courseId") int courseId, @Param("userId") int userId, @Param("score") int score, @Param("info") String info);

    /**
     * 更新评论
     *
     * @param id    评论编号
     * @param good  评论获赞数
     * @param state 评论合法状态
     * @return 返回1-更新成功，返回0-更新失败
     */
    int updateCommentGood(@Param("id") int id, @Param("good") int good, @Param("state") int state);

    /**
     * 删除评论
     *
     * @param id 评论编号
     * @return 删除成功返回1，否则返回0
     */
    int deleteComment(@Param("id") int id);

    /**
     * 查询某课程下的评论
     *
     * @param courseId 课程编号
     * @return 评论全部信息
     */
    List<Comment> getCommentById(@Param("courseId") int courseId);

    /**
     * 查询所有评论
     *
     * @return 评论全部信息
     */
    List<Comment> getComment(Map<String, Object> map);
}