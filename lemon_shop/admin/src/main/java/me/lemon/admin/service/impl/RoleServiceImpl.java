package me.lemon.admin.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lemon.admin.dto.UserDto;
import me.lemon.admin.dto.RoleDto;
import me.lemon.admin.service.RoleService;
import me.lemon.database.mapper.RoleMapper;
import me.lemon.database.mapper.UsersAndRolesMapper;
import me.lemon.database.model.Role;
import me.lemon.database.model.RoleExample;
import me.lemon.database.model.UsersAndRoles;
import me.lemon.database.model.UsersAndRolesExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "role")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    private RoleDto dto;

    @Autowired
    UsersAndRolesMapper usersAndRolesMapper;

    @Override
    public List<RoleDto> queryAll() {

        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRoleIdIsNotNull();

        List<Role> roleList = roleMapper.selectByExample(roleExample);
        List<RoleDto> roleDtoList = new ArrayList<>();
        roleList.forEach(role -> {
            RoleDto dto = new RoleDto();
            BeanUtils.copyProperties(role, dto);
            roleDtoList.add(dto);
        });

        return roleDtoList;
    }

    @Override
    public List<RoleDto> queryAll(Pageable pageable) {

        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());

        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRoleIdIsNotNull();

        List<Role> roleList = roleMapper.selectByExample(roleExample);

        int count = roleMapper.countByExample(roleExample);
        List<RoleDto> roleDtoList = new ArrayList<>();
        roleList.forEach(role -> {
            RoleDto dto = new RoleDto();
            dto.setTotal(count);
            BeanUtils.copyProperties(role, dto);
            roleDtoList.add(dto);
        });

        return roleDtoList;
    }

    /**
     * 根据roleid查询用户的角色
     *
     * @param id /
     * @return
     */
    @Override
    public RoleDto findById(long id) {

        Role role = roleMapper.selectByPrimaryKey(id);
        RoleDto dto = new RoleDto();
        BeanUtils.copyProperties(role, dto);

        return dto;
    }

    @Override
    public void create(Role resources) {

    }

    @Override
    public void update(Role resources) {

    }

    @Override
    public void delete(Set<Long> ids) {

    }

    /**
     * 根据用户id查询用户的角色
     *
     * @param id 用户ID
     * @return
     */
    @Override
    public List<RoleDto> findByUsersId(Long id) {

        UsersAndRolesExample rolesExample = new UsersAndRolesExample();
        rolesExample.createCriteria().andUserIdEqualTo(id);

        List<UsersAndRoles> usersAndRoles = usersAndRolesMapper.selectByExample(rolesExample);

        List<RoleDto> roleList = new ArrayList<>();
        if (usersAndRoles != null && usersAndRoles.size() > 0) {
            for (int i = 0; i < usersAndRoles.size(); i++) {
                Role role = roleMapper.selectByPrimaryKey(usersAndRoles.get(i).getRoleId());

                if (role != null) {

                    RoleDto roleDto = new RoleDto();

                    log.info("----------------获取角色信息" + role.getRoleId());
                    BeanUtils.copyProperties(role, roleDto);
                    roleList.add(roleDto);
                }
            }
        }
        return roleList;
    }

    @Override
    public Integer findByRoles(Set<Role> roles) {
        return null;
    }

    @Override
    public void updateMenu(Role resources, RoleDto roleDTO) {

    }

    @Override
    public void untiedMenu(Long id) {

    }

    @Override
    public void download(List<RoleDto> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDto user) {
        return null;
    }

    @Override
    public void verification(Set<Long> ids) {

    }

    @Override
    public List<Role> findInMenuId(List<Long> menuIds) {
        return null;
    }
}
