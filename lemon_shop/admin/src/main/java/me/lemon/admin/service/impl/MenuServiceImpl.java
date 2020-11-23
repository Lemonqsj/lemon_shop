package me.lemon.admin.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lemon.admin.dto.MenuDto;
import me.lemon.admin.dto.RoleDto;
import me.lemon.admin.service.MenuService;
import me.lemon.admin.service.RoleService;
import me.lemon.database.mapper.MenuMapper;
import me.lemon.database.mapper.RolesAndMenusMapper;
import me.lemon.database.model.Menu;
import me.lemon.database.model.MenuExample;
import me.lemon.database.model.RolesAndMenus;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "menu")
public class MenuServiceImpl implements MenuService {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    RolesAndMenusMapper rolesAndMenusMapper;

    @Override
    public MenuDto findById(long id) {
        return null;
    }

    @Override
    public void create(Menu resources) {

    }

    @Override
    public void update(Menu resources) {

    }

    @Override
    public Set<Menu> getDeleteMenus(List<Menu> menuList, Set<Menu> menuSet) {
        return null;
    }

    @Override
    public List<MenuDto> buildTree(List<MenuDto> menuDtos, Long pid) {

        List<MenuDto> treeList = new ArrayList<>();
        menuDtos.forEach(menuDto -> {
            if (StringUtils.equals(pid + "", menuDto.getPid() + "")) {
                menuDto.setChildren(buildTree(menuDtos, menuDto.getMenuId()));
                treeList.add(menuDto);
            }
        });
        return treeList;
    }

    @Override
    public Object buildMenus(List<MenuDto> menuDtos) {
        return null;
    }

    @Override
    public Menu findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Set<Menu> menuSet) {

    }

    @Override
    public void download(List<MenuDto> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public List<MenuDto> getMenus(String pid) {

        MenuExample example = new MenuExample();

        if (StringUtils.isEmpty(pid)||pid==null||"null".equals(pid)){
            example.createCriteria().andTypeEqualTo(0);
        }else {
            example.createCriteria().andPidEqualTo(Long.valueOf(pid));
        }


        List<Menu> menuList = menuMapper.selectByExample(example);

        if (menuList != null && menuList.size() > 0) {
            List<MenuDto> menuDtos = new ArrayList<>();
            for (Menu m : menuList) {
                MenuDto dto = new MenuDto();
                BeanUtils.copyProperties(m, dto);
                menuDtos.add(dto);
            }

            log.info("---------pid"+pid+"---------"+menuDtos);

            return menuDtos;
        }
        return null;
    }

    @Override
    public List<MenuDto> getSuperior(MenuDto menuDto, List<Menu> objects) {
        return null;
    }

    @Override
    public List<MenuDto> findByUser(Long currentUserId) {
        List<RoleDto> byUsersId = roleService.findByUsersId(currentUserId);
        List<Long> list = byUsersId.stream().map(RoleDto::getRoleId).collect(Collectors.toList());
        List<Menu> menus = menuMapper.findMenuByRoleIdsAndType(list, 2);

        if (menus != null && menus.size() > 0) {
            List<MenuDto> menuDtos = new ArrayList<>();
            for (Menu m : menus) {
                MenuDto dto = new MenuDto();
                BeanUtils.copyProperties(m, dto);
                menuDtos.add(dto);
            }


            return menuDtos;
        }
        return null;
    }
}
