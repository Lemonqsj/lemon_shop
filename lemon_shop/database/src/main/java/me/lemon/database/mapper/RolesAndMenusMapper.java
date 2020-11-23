package me.lemon.database.mapper;

import java.util.List;
import me.lemon.database.model.RolesAndMenus;
import me.lemon.database.model.RolesAndMenusExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesAndMenusMapper {
    int countByExample(RolesAndMenusExample example);

    int deleteByExample(RolesAndMenusExample example);

    int deleteByPrimaryKey(@Param("menuId") Long menuId, @Param("roleId") Long roleId);

    int insert(RolesAndMenus record);

    int insertSelective(RolesAndMenus record);

    List<RolesAndMenus> selectByExample(RolesAndMenusExample example);

    int updateByExampleSelective(@Param("record") RolesAndMenus record, @Param("example") RolesAndMenusExample example);

    int updateByExample(@Param("record") RolesAndMenus record, @Param("example") RolesAndMenusExample example);
}