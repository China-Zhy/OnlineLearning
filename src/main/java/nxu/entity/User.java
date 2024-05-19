package nxu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户实体类
 */
@Data
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
    private Role role;          // 身份标识
}