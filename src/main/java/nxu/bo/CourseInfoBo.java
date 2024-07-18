package nxu.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nxu.entity.Course;
import nxu.entity.User;

import java.util.List;

/**
 * 课程信息页数据业务实体类 (张宏业)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseInfoBo {
    private Course course;              // 课程信息
    private User teacher;               // 任课教师
    private List<CommentBo> commentBo;  // 课程下评论
}