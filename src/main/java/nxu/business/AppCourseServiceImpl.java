package nxu.business;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import nxu.bo.CommentBo;
import nxu.bo.CourseDataBo;
import nxu.bo.CourseInfoBo;
import nxu.dao.UserDaoImpl;
import nxu.entity.Comment;
import nxu.entity.Course;
import nxu.entity.CourseType;
import nxu.entity.User;
import nxu.mapper.CommentMapper;
import nxu.mapper.CourseMapper;
import nxu.mapper.CourseTypeMapper;
import nxu.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 前端页面数据服务层接口实现类 (张宏业)
 */
public class AppCourseServiceImpl implements AppCourseService {

    /**
     * 获取课程详细信息页面数据
     *
     * @param courseId 课程编号
     * @return 课程详细信息集合(Course 、 User 、 List < Comment >)
     */
    @Override
    public CourseInfoBo getCourseInfoBo(int courseId) {

        CourseInfoBo courseInfoBo = new CourseInfoBo();

        HashMap<String, Object> courseMap = new HashMap<>();
        courseMap.put("id", courseId);

        SqlSession sqlSession = MybatisUtil.getSqlSession();

        Course course = sqlSession.getMapper(CourseMapper.class).getCourse(courseMap).getFirst();
        courseInfoBo.setCourse(course);

        User teacher = new UserDaoImpl().queryUserById(course.getUserId());
        courseInfoBo.setTeacher(teacher);

        List<Comment> comments = sqlSession.getMapper(CommentMapper.class).getCommentById(courseId);

        List<CommentBo> commentBoList = new ArrayList<>();

        for (Comment comment : comments) {
            User user = new UserDaoImpl().queryUserById(comment.getUserId());
            CommentBo commentBo = new CommentBo();
            commentBo.setUser(user);
            commentBo.setComment(comment);
            commentBoList.add(commentBo);
        }
        courseInfoBo.setCommentBo(commentBoList);
        return courseInfoBo;
    }

    /**
     * 获取全部种类课程首页数据
     *
     * @return 全部种类的课程信息集合(CourseType 、 List < Course >)
     */
    @Override
    public List<CourseDataBo> getCourseDataBo() {

        List<CourseDataBo> courseDataBoList = new ArrayList<>();

        HashMap<String, Object> map = new HashMap<>();
        map.put("pageIndex", 1);
        map.put("pageSize", 10);   // 查询全部课程种类

        SqlSession sqlSession = MybatisUtil.getSqlSession();
        List<CourseType> courseType = sqlSession.getMapper(CourseTypeMapper.class).getCourseType(map);

        map.put("pageSize", 9);   // 页面需求，每种课程最多显示9个

        for (CourseType type : courseType) {
            CourseDataBo courseDataBo = new CourseDataBo();
            courseDataBo.setCourseType(type);
            map.put("courseType", type.getId());
            PageHelper.startPage((int) (map.get("pageIndex")), (int) map.get("pageSize"));
            List<Course> course = sqlSession.getMapper(CourseMapper.class).getCourse(map);
            courseDataBo.setCourses(new PageInfo<>(course).getList());
            courseDataBo.setCourseCount(course.size());
            courseDataBoList.add(courseDataBo);
        }
        sqlSession.close();
        return courseDataBoList;
    }
}