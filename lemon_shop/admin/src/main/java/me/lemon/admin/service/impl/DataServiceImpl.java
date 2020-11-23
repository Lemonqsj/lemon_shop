package me.lemon.admin.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lemon.admin.dto.RoleDto;
import me.lemon.admin.dto.UserDto;
import me.lemon.admin.service.DataService;
import me.lemon.admin.service.DeptService;
import me.lemon.admin.service.RoleService;
import me.lemon.admin.utils.DataScopeEnum;
import me.lemon.database.model.Dept;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "data")
@Slf4j
public class DataServiceImpl implements DataService {


    @Autowired
    private RoleService roleService;

    @Autowired
    private DeptService deptService;

    @Override
    @Cacheable(key = "'user:' + #p0.id")
    public List<Long> getDeptIds(UserDto user) {

        // 用于存储部门id
        Set<Long> deptIds = new HashSet<>();

        List<RoleDto> roleDtos = roleService.findByUsersId(user.getUser().getUserId());

//        log.info("-------roleDtos----"+roleDtos);

        for (RoleDto role: roleDtos) {

            DataScopeEnum dataScopeEnum = DataScopeEnum.find(role.getDataScope());
            log.info("-------dataScopeEnum--user--"+user.getUser().getUsername()+"------datascope----"+dataScopeEnum);

            switch (Objects.requireNonNull(dataScopeEnum)){

                case THIS_LEVEL:
                    deptIds.add(user.getDept().getDeptId());
                    break;
                case CUSTOMIZE:
                    deptIds.addAll(getCustomize(deptIds, role));
                    break;
                default:
                    return new ArrayList<>(deptIds);
            }

        }
        return null;
    }

    /**
     * 获取自定义的数据权限
     * @param deptIds 部门ID
     * @param role 角色
     * @return 数据权限ID
     */
    public Set<Long> getCustomize(Set<Long> deptIds, RoleDto role){
        Set<Dept> depts = deptService.findByRoleId(role.getRoleId());
        for (Dept dept : depts) {
            deptIds.add(dept.getDeptId());
            List<Dept> deptChildren = deptService.findByPid(dept.getDeptId());
            if (deptChildren != null && deptChildren.size() != 0) {
                deptIds.addAll(deptService.getDeptChildren(dept.getDeptId(), deptChildren));
            }
        }
        log.info("-----------获取自定义权限---"+deptIds);
        return deptIds;
    }
}
