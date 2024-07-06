package nxu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 用户实体类 (张宏业)
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;             // 用户编号(主键)
    private String name;        // 用户昵称
    private int gender;         // 用户性别(0-男 1-女)
    private String phone;       // 手机号码
    private String email;       // 电子邮箱
    private String password;    // 账户密码
    private String image;       // 用户头像
    private Date register;      // 注册时间
    private int score;          // 用户积分
    private int type;           // 身份标识(外键)
    private String info;        // 其他信息
    private int state;          // 用户状态(0-禁用 1-可用)
}