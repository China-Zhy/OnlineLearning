package nxu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 文件实体类 (张宏业)
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private int id;             // 文件编号(主键)
    private String name;        // 文件名称
    private String path;        // 文件路径
    private Date upload;        // 上传时间
    private int type;           // 文件类型(外键)
    private String info;        // 描述信息
    private int state;          // 文件状态(1-禁用 2-可用只读 3-可用下载)
}