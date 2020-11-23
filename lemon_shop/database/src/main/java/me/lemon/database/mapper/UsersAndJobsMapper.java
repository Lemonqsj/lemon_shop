package me.lemon.database.mapper;

import java.util.List;
import me.lemon.database.model.UsersAndJobs;
import me.lemon.database.model.UsersAndJobsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersAndJobsMapper {
    int countByExample(UsersAndJobsExample example);

    int deleteByExample(UsersAndJobsExample example);

    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("jobId") Long jobId);

    int insert(UsersAndJobs record);

    int insertSelective(UsersAndJobs record);

    List<UsersAndJobs> selectByExample(UsersAndJobsExample example);

    int updateByExampleSelective(@Param("record") UsersAndJobs record, @Param("example") UsersAndJobsExample example);

    int updateByExample(@Param("record") UsersAndJobs record, @Param("example") UsersAndJobsExample example);
}