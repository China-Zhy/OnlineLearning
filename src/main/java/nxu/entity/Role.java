package nxu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 角色实体类 (张宏业)
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private int id;             // 角色编号(主键)
    private String identity;    // 身份标识名
}