package nxu.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nxu.entity.Course;
import nxu.entity.CourseType;

import java.util.List;

/**
 * 课程首页信息业务实体类 (张宏业)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseDataBo {
    private CourseType courseType;      // 课程类型信息
    private List<Course> courses;       // 该类课程信息
}