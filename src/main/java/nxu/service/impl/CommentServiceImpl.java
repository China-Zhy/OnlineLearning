package nxu.service.impl;

import nxu.entity.Comment;
import nxu.mapper.CommentMapper;
import nxu.service.CommentService;
import nxu.utils.MybatisUtil;

import java.util.List;
import java.util.Map;

/**
 * 评论服务层实现类 (唐馨源)
 */
public class CommentServiceImpl implements CommentService {

    /**
     * 插入评论
     *
     * @param courseId 评论课程编号
     * @param userId   评论用户编号
     * @param score    评论星级
     * @param info     评论内容
     * @return 插入成功返回1，否则返回0
     */
    @Override
    public int insertComment(int courseId, int userId, int score, String info) {
        int i = MybatisUtil.getSqlSession().getMapper(CommentMapper.class).insertComment(courseId, userId, score, info);
        MybatisUtil.getSqlSession().close();
        return i;
    }

    /**
     * 更新评论
     *
     * @param id    评论编号
     * @param good  评论获赞数
     * @param state 评论合法状态
     * @return 返回-1更新成功，返回-0更新失败
     */
    @Override
    public int updateCommentGood(int id, int good, int state) {
        int i = MybatisUtil.getSqlSession().getMapper(CommentMapper.class).updateCommentGood(id, good, state);
        MybatisUtil.getSqlSession().close();
        return i;
    }

    /**
     * 删除评论
     *
     * @param id 评论编号
     * @return 删除成功返回1，否则返回0
     */
    @Override
    public int deleteComment(int id) {
        int i = MybatisUtil.getSqlSession().getMapper(CommentMapper.class).deleteComment(id);
        MybatisUtil.getSqlSession().close();
        return i;
    }

    /**
     * 查询某课程下的评论
     *
     * @param courseId 课程编号
     * @return 评论全部信息
     */
    @Override
    public List<Comment> getCommentById(int courseId) {
        List<Comment> comment = MybatisUtil.getSqlSession().getMapper(CommentMapper.class).getCommentById(courseId);
        MybatisUtil.getSqlSession().close();
        return comment;
    }

    /**
     * 查询所有评论
     *
     * @return 评论全部信息
     */
    @Override
    public List<Comment> getComment(Map<String, Object> map) {
        List<Comment> comment = MybatisUtil.getSqlSession().getMapper(CommentMapper.class).getComment(map);
        MybatisUtil.getSqlSession().close();
        return comment;
    }
}
