package nxu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import nxu.entity.File;
import nxu.mapper.FileMapper;
import nxu.service.FileService;
import nxu.utils.MybatisUtil;

import java.util.List;
import java.util.Map;

/**
 * 文件服务层接口实现类 (张宏业)
 */
public class FileServiceImpl implements FileService {

    /**
     * 通过文件编号查询文件信息
     *
     * @param id 文件编号
     * @return 单个文件实体
     */
    @Override
    public File getFileById(int id) {
        File file = MybatisUtil.getSqlSession().getMapper(FileMapper.class).getFileById(id);
        MybatisUtil.getSqlSession().close();
        return file;
    }

    /**
     * 通过指定条件查询多个文件实体
     *
     * @param map 条件参数(entity、target、pageIndex、pageSize)
     * @return 带有分页功能的文件实体集合
     */
    @Override
    public PageInfo<File> getAllFiles(Map<String, Object> map) {
        PageHelper.startPage((int) map.get("pageIndex"), (int) map.get("pageSize"));
        List<File> allFiles = MybatisUtil.getSqlSession().getMapper(FileMapper.class).getAllFiles(map);
        MybatisUtil.getSqlSession().close();
        return new PageInfo<>(allFiles);
    }

    /**
     * 添加一个文件
     *
     * @param file 文件实体类
     * @return 返回1-添加成功，返回0-添加失败
     */
    @Override
    public int insertFile(File file) {
        int i = MybatisUtil.getSqlSession().getMapper(FileMapper.class).insertFile(file);
        MybatisUtil.getSqlSession().close();
        return i;
    }

    /**
     * 通过文件编号删除单个文件
     *
     * @param id 文件编号
     * @return 返回1-删除成功，返回0-删除失败
     */
    @Override
    public int deleteFileById(int id) {
        int i = MybatisUtil.getSqlSession().getMapper(FileMapper.class).deleteFileById(id);
        MybatisUtil.getSqlSession().close();
        return i;
    }
}