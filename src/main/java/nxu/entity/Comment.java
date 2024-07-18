package nxu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 评论实体类 (张宏业)
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int id;             // 评论编号(主键)
    private int courseId;       // 课程编号(外键)
    private int userId;         // 用户编号(外键)
    private int score;          // 评价星级
    private String info;        // 评论内容
    private Date time;          // 评论时间
    private int good;           // 获赞数量
    private int state;          // 评论状态(1-未经审核 2-审核通过 3-审核禁止)
}