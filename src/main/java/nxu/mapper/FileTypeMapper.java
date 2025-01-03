package nxu.mapper;

import nxu.entity.FileType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文件类型数据层接口 (张宏业)
 */
public interface FileTypeMapper {

    /**
     * 通过文件类型编号查询文件类型名称
     *
     * @param id 文件类型编号
     * @return 文件类型名称
     */
    String getFileTypeNameById(int id);

    /**
     * 查询全部文件类型
     *
     * @return 文件类型集合
     */
    List<FileType> getAllFileType();

    /**
     * 添加文件类型
     *
     * @param name 文件类型名称
     * @return 返回1-添加成功，返回0-添加失败
     */
    int insertFileType(String name);

    /**
     * 通过文件类型编号修改文件类型名称
     *
     * @param id   文件类型编号
     * @param name 文件类型名称
     * @return 返回1-修改成功，返回0-修改失败
     */
    int updateFileType(@Param("id") int id, @Param("name") String name);

    /**
     * 通过文件类型编号删除文件类型
     *
     * @param id 文件类型编号
     * @return 返回1-删除成功，返回0-删除失败
     */
    int deleteFileTypeById(int id);
}