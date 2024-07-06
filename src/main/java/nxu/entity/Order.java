package nxu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 订单实体类 (张宏业)
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id;             // 订单编号(主键)
    private int userId;         // 用户编号(外键)
    private int courseId;       // 课程编号(外键)
    private Date time;          // 下单时间
    private int state;          // 订单状态(0-待支付 1-购买成功 2-购买失败)
}