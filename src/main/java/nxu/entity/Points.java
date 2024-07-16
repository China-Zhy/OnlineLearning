package nxu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 积分记录实体类 (张宏业)
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Points {
    private int id;         // 积分记录编号(主键)
    private int userId;     // 用户编号(外键)
    private String type;    // 来源类型(用户充值、用户消费、学习所得)
    private int number;     // 积分数量(正负)
    private Date time;      // 积分变动时间
}