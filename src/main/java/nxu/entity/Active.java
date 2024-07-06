package nxu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 活动实体类 (张宏业)
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Active {
    private int id;             // 活动编号(主键)
    private String title;       // 活动标题
    private String info;        // 活动内容
    private String image;       // 活动图片
    private float discount;     // 折扣力度
    private Date create;        // 创建时间
    private Date deadline;      // 截止时间
    private int userId;         // 创建用户(外键)
}