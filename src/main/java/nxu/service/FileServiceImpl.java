package nxu.service;

import nxu.entity.File;
import nxu.mapper.FileMapper;
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
        return MybatisUtil.getSqlSession().getMapper(FileMapper.class).getFileById(id);
    }

    /**
     * 通过指定条件查询多个文件实体
     *
     * @param map 条件参数
     * @return 文件实体集合
     */
    @Override
    public List<File> getAllFiles(Map<String, Object> map) {
        return MybatisUtil.getSqlSession().getMapper(FileMapper.class).getAllFiles(map);
    }
}