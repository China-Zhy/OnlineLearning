package nxu.mapper;

import nxu.entity.File;

import java.util.List;
import java.util.Map;

/**
 * 文件数据层接口 (张宏业)
 */
public interface FileMapper {

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
     * @param map 条件参数(entity、target)
     * @return 文件实体集合
     */
    List<File> getAllFiles(Map<String, Object> map);

    /**
     * 添加一个文件
     *
     * @param file 文件实体类
     * @return 返回1-添加成功，返回0-添加失败
     */
    int insertFile(File file);

    /**
     * 通过文件编号删除单个文件
     *
     * @param id 文件编号
     * @return 返回1-删除成功，返回0-删除失败
     */
    int deleteFileById(int id);
}