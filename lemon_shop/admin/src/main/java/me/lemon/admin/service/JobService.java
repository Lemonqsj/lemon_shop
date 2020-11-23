package me.lemon.admin.service;

import me.lemon.admin.dto.JobDto;
import me.lemon.database.model.Job;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JobService {

    /**
     * 根据ID查询
     * @param id /
     * @return /
     */
    JobDto findById(Long id);

    /**
     * 根据userid获取相对应的职位信息
     *
     * @param userId
     * @return
     */
    List<JobDto> findByUserId(Long userId);

    /**
     * 创建
     * @param resources /
     * @return /
     */
    void create(Job resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Job resources);

    /**
     * 删除
     * @param ids /
     */
    void delete(Set<Long> ids);

    /**
     * 分页查询
     * @param
     * @param pageable 分页参数
     * @return /
     */
    List<JobDto> queryAll( Pageable pageable);

    /**
     * 查询全部数据
     * @param  /
     * @return /
     */
    List<JobDto> queryAll();

    /**
     * 导出数据
     * @param queryAll 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<JobDto> queryAll, HttpServletResponse response) throws IOException;

    /**
     * 验证是否被用户关联
     * @param ids /
     */
    void verification(Set<Long> ids);
}
