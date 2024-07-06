package nxu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 课程邀请码实体类 (张宏业)
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourseCode {
    private int userId;         // 用户编号(外键)
    private int courseId;       // 课程编号(外键)
    private String code;        // 课程邀请码(6位)
}