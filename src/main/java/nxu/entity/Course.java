package nxu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 课程实体类 (张宏业)
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int id;                 // 课程编号(主键)
    private String name;            // 课程名称
    private int courseType;         // 课程类型(外键)
    private String image;           // 课程图片
    private String info;            // 课程描述
    private int score;              // 课程积分
    private int state;              // 课程状态(0-正常 1-折扣 2-限免)
    private int userId;             // 创建用户(外键)
}