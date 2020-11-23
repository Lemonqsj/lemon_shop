package me.lemon.database.mapper;

import java.util.List;
import me.lemon.database.model.RolesAndDepts;
import me.lemon.database.model.RolesAndDeptsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesAndDeptsMapper {
    int countByExample(RolesAndDeptsExample example);

    int deleteByExample(RolesAndDeptsExample example);

    int deleteByPrimaryKey(@Param("roleId") Long roleId, @Param("deptId") Long deptId);

    int insert(RolesAndDepts record);

    int insertSelective(RolesAndDepts record);

    List<RolesAndDepts> selectByExample(RolesAndDeptsExample example);

    int updateByExampleSelective(@Param("record") RolesAndDepts record, @Param("example") RolesAndDeptsExample example);

    int updateByExample(@Param("record") RolesAndDepts record, @Param("example") RolesAndDeptsExample example);
}