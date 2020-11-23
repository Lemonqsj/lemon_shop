package me.lemon.admin.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import me.lemon.admin.dto.JobDto;
import me.lemon.admin.service.JobService;
import me.lemon.database.mapper.JobMapper;
import me.lemon.database.mapper.UsersAndJobsMapper;
import me.lemon.database.model.Job;
import me.lemon.database.model.JobExample;
import me.lemon.database.model.UsersAndJobs;
import me.lemon.database.model.UsersAndJobsExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "job")
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private UsersAndJobsMapper usersAndJobsMapper;
    @Override
    public JobDto findById(Long id) {
        Job job = jobMapper.selectByPrimaryKey(id);
        JobDto  dto=new JobDto();
        BeanUtils.copyProperties(job,dto);

        return dto;
    }

    @Override
    public List<JobDto> findByUserId(Long userId) {
        UsersAndJobsExample example=new UsersAndJobsExample();

        example.createCriteria().andUserIdEqualTo(userId);
        List<UsersAndJobs> usersAndJobs = usersAndJobsMapper.selectByExample(example);

        List<JobDto> jobList=new ArrayList<>();
        if (usersAndJobs!=null&&usersAndJobs.size()>0){
            for (int i=0;i<usersAndJobs.size();i++){
                Job job = jobMapper.selectByPrimaryKey(usersAndJobs.get(i).getJobId());

                if (job!=null){

                    JobDto dto=new JobDto();
                    BeanUtils.copyProperties(job,dto);
                    jobList.add(dto);
//                    log.info("工作信息信息："+job.toString());
                }
            }
        }


        return jobList;
    }

    @Override
    public void create(Job resources) {

    }

    @Override
    public void update(Job resources) {

    }

    @Override
    public void delete(Set<Long> ids) {

    }

    /**
     * 分页查询工作岗位
     * @param pageable 分页参数
     * @return
     */
    @Override
    public List<JobDto> queryAll(Pageable pageable) {

        PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize());


        JobExample example=new JobExample();

        example.createCriteria().andJobIdIsNotNull();

        List<Job> jobList = jobMapper.selectByExample(example);

        int count = jobMapper.countByExample(example);
        List<JobDto> dtos=new ArrayList<>();

        jobList.forEach(job -> {
            JobDto dto=new JobDto();
            dto.setTotal(count);
            BeanUtils.copyProperties(job,dto);
            dtos.add(dto);
        });
        return dtos;
    }

    /**
     * 查询所有的岗位
     * @return
     */
    @Override
    public List<JobDto> queryAll() {
        JobExample example=new JobExample();

        example.createCriteria().andJobIdIsNotNull();

        List<Job> jobList = jobMapper.selectByExample(example);
        List<JobDto> dtos=new ArrayList<>();

        jobList.forEach(job -> {
            JobDto dto=new JobDto();
            BeanUtils.copyProperties(job,dto);
            dtos.add(dto);
        });
        return dtos;
    }

    @Override
    public void download(List<JobDto> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public void verification(Set<Long> ids) {

    }
}
