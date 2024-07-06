package nxu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 公告实体类 (张宏业)
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    private int id;             // 公告编号(主键)
    private String title;       // 公告标题
    private String info;        // 公告内容
    private Date create;        // 创建时间
    private Date dateline;      // 截止时间
    private int target;         // 公告目标(0-系统公告 其他-课程公告)
    private int userId;         // 创建用户编号(外键)
}