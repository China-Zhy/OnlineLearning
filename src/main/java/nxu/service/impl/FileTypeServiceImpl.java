package nxu.service.impl;

import nxu.entity.FileType;
import nxu.mapper.FileTypeMapper;
import nxu.service.FileTypeService;
import nxu.utils.MybatisUtil;

import java.util.List;

/**
 * 文件类型服务层接口实现类 (张宏业)
 */
public class FileTypeServiceImpl implements FileTypeService {

    /**
     * 通过文件类型编号查询文件类型名称
     *
     * @param id 文件类型编号
     * @return 文件类型名称
     */
    @Override
    public String getFileTypeNameById(int id) {
        return MybatisUtil.getSqlSession().getMapper(FileTypeMapper.class).getFileTypeNameById(id);
    }

    /**
     * 查询全部文件类型
     *
     * @param pageIndex 当前页码 (0-表示不分页)
     * @param pageSize  每页数据量 (0-表示不分页)
     * @return 文件类型集合(通过数组进行分页)
     */
    @Override
    public List<FileType> getAllFileType(int pageIndex, int pageSize) {
        List<FileType> fileTypeList = MybatisUtil.getSqlSession().getMapper(FileTypeMapper.class).getAllFileType();
        if (pageIndex > 0 && pageSize > 0) {
            int beginIndex = Math.min((pageIndex - 1) * pageSize, fileTypeList.size());
            int endIndex = Math.min((pageIndex * pageSize), fileTypeList.size());
            return fileTypeList.subList(beginIndex, endIndex);
        } else {
            return fileTypeList;
        }
    }

    /**
     * 添加文件类型
     *
     * @param name 文件类型名称
     * @return 返回1-添加成功，返回0-添加失败
     */
    @Override
    public int insertFileType(String name) {
        return MybatisUtil.getSqlSession().getMapper(FileTypeMapper.class).insertFileType(name);
    }

    /**
     * 通过文件类型编号修改文件类型名称
     *
     * @param id   文件类型编号
     * @param name 文件类型名称
     * @return 返回1-修改成功，返回0-修改失败
     */
    @Override
    public int updateFileType(int id, String name) {
        return MybatisUtil.getSqlSession().getMapper(FileTypeMapper.class).updateFileType(id, name);
    }

    /**
     * 通过文件类型编号删除文件类型
     *
     * @param id 文件类型编号
     * @return 返回1-删除成功，返回0-删除失败
     */
    @Override
    public int deleteFileTypeById(int id) {
        return MybatisUtil.getSqlSession().getMapper(FileTypeMapper.class).deleteFileTypeById(id);
    }
}