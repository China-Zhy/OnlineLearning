package nxu.business;

import com.github.pagehelper.PageInfo;
import nxu.bo.CommentBo;
import nxu.bo.CourseDataBo;
import nxu.bo.CourseInfoBo;
import nxu.entity.Comment;
import nxu.entity.Course;
import nxu.entity.CourseType;
import nxu.entity.User;
import nxu.service.*;
import nxu.service.impl.CommentServiceImpl;
import nxu.service.impl.CourseServiceImpl;
import nxu.service.impl.CourseTypeServiceImpl;
import nxu.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 页面数据服务层接口实现类 (张宏业)
 */
public class AppCourseServiceImpl implements AppCourseService {

    private static final CourseService courseService = new CourseServiceImpl();

    private static final UserService userService = new UserServiceImpl();

    private static final CommentService commentService = new CommentServiceImpl();

    private static final CourseTypeService courseTypeService = new CourseTypeServiceImpl();

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
        courseMap.put("pageIndex", 1);
        courseMap.put("pageSize", 9);   // 页面需求，每种课程最多显示9个
        courseMap.put("id", courseId);
        PageInfo<Course> coursePI = courseService.getCourse(courseMap);

        Course course = coursePI.getList().getFirst();
        courseInfoBo.setCourse(course);

        User teacher = userService.queryUserById(course.getUserId());
        courseInfoBo.setTeacher(teacher);

        List<Comment> comments = commentService.getCommentById(course.getId());

        List<CommentBo> commentBoList = new ArrayList<>();

        for (Comment comment : comments) {
            User user = userService.queryUserById(comment.getUserId());
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
        map.put("pageSize", 100);
        PageInfo<CourseType> courseTypePI = courseTypeService.getCourseType(map);

        HashMap<String, Object> courseMap = new HashMap<>();

        for (CourseType courseType : courseTypePI.getList()) {
            CourseDataBo courseDataBo = new CourseDataBo();
            courseDataBo.setCourseType(courseType);
            courseMap.put("pageIndex", 1);
            courseMap.put("pageSize", 9);
            courseMap.put("courseType", courseType.getId());
            PageInfo<Course> course = courseService.getCourse(courseMap);
            courseDataBo.setCourses(course.getList());
            courseDataBoList.add(courseDataBo);
        }
        return courseDataBoList;
    }
}