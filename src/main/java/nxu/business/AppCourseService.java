package nxu.business;

import nxu.bo.CourseDataBo;
import nxu.bo.CourseInfoBo;

import java.util.List;

/**
 * 页面数据服务层接口 (张宏业)
 */
public interface AppCourseService {

    /**
     * 获取课程详细信息页面数据
     *
     * @param courseId 课程编号
     * @return 课程详细信息集合(Course 、 User 、 List < Comment >)
     */
    CourseInfoBo getCourseInfoBo(int courseId);

    /**
     * 获取全部种类课程首页数据
     *
     * @return 全部种类的课程信息集合(CourseType 、 List < Course >)
     */
    List<CourseDataBo> getCourseDataBo();
}