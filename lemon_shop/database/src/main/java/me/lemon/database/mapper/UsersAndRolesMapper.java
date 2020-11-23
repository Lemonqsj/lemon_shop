package me.lemon.database.mapper;

import java.util.List;
import me.lemon.database.model.UsersAndRoles;
import me.lemon.database.model.UsersAndRolesExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersAndRolesMapper {
    int countByExample(UsersAndRolesExample example);

    int deleteByExample(UsersAndRolesExample example);

    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("roleId") Long roleId);

    int insert(UsersAndRoles record);

    int insertSelective(UsersAndRoles record);

    List<UsersAndRoles> selectByExample(UsersAndRolesExample example);

    int updateByExampleSelective(@Param("record") UsersAndRoles record, @Param("example") UsersAndRolesExample example);

    int updateByExample(@Param("record") UsersAndRoles record, @Param("example") UsersAndRolesExample example);
}