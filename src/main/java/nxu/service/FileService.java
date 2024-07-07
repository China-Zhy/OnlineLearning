package nxu.service;

import nxu.entity.File;

import java.util.List;
import java.util.Map;

/**
 * 文件服务层接口 (张宏业)
 */
public interface FileService {

    /**
     * 通过文件编号查询文件信息
     *
     * @param id 文件编号
     * @return 单个文件实体
     */
    File getFileById(int id);

    /**
     * 通过指定条件查询多个文件实体
     *
     * @param map 条件参数
     * @return 文件实体集合
     */
    List<File> getAllFiles(Map<String, Object> map);
}