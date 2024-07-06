package nxu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 私信实体类 (张宏业)
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private int id;             // 私信编号(主键)
    private int sender;         // 发送人员(外键)
    private int accept;         // 接受人员(外键)
    private String info;        // 私信内容
    private Date time;          // 发送时间
}