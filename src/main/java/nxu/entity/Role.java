package nxu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private int id;         // 角色编号(主键)
    private String name;    // 身份标识名
}