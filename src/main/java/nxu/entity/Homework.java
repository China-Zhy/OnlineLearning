package nxu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 作业实体类 (张宏业)
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Homework {
    private int id;             // 作业编号(主键)
    private String title;       // 作业标题
    private String info;        // 作业描述
    private int courseId;       // 课程编号(外键)
    private int userId;         // 用户编号(外键)
    private Date create;        // 创建时间
    private Date dateline;      // 截止时间
    private int again;          // 提交约束(1-允许 2-禁止)
}