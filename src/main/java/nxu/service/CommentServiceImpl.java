package nxu.service;

import nxu.entity.Comment;
import nxu.mapper.CommentMapper;
import nxu.utils.MybatisUtil;

import java.util.Date;

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
        return MybatisUtil.getSqlSession().getMapper(CommentMapper.class).insertComment(courseId, userId, score, info);
    }

    /**
     * 更新获赞数
     *
     * @param id 评论编号
     * @return 更新成功返回1，否则返回0
     */
    @Override
    public int updateCommentGood(int id) {
        return MybatisUtil.getSqlSession().getMapper(CommentMapper.class).updateCommentGood(id);
    }

    /**
     * 删除评论
     *
     * @param id 评论编号
     * @return 删除成功返回1，否则返回0
     */
    @Override
    public int deleteComment(int id) {
        return MybatisUtil.getSqlSession().getMapper(CommentMapper.class).deleteComment(id);
    }

    /**
     * 查询某课程下的评论
     *
     * @param courseId 课程编号
     * @return 评论全部信息
     */
    @Override
    public Comment getComment(int courseId) {
        return MybatisUtil.getSqlSession().getMapper(CommentMapper.class).getComment(courseId);
    }

}
