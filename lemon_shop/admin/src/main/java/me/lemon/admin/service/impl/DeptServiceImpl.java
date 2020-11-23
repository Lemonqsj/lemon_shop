package me.lemon.admin.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lemon.admin.dto.DeptDto;
import me.lemon.admin.dto.MenuDto;
import me.lemon.admin.service.DeptService;
import me.lemon.admin.service.RoleService;
import me.lemon.database.mapper.DeptMapper;
import me.lemon.database.mapper.RolesAndDeptsMapper;
import me.lemon.database.model.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "dept")
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptMapper deptMapper;

    @Autowired
    RoleService roleService;

    @Autowired
    RolesAndDeptsMapper rolesAndDeptsMapper;
    @Override
    public List<DeptDto> queryAll() throws Exception {

        DeptExample example=new DeptExample();
        example.createCriteria().andDeptIdIsNotNull();


        List<Dept> depts = deptMapper.selectByExample(example);


        log.info("depts"+depts);

        return null;
    }

    @Override
    public List<DeptDto> queryAll(org.springframework.data.domain.Pageable pageable) {
//        PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize());

        DeptExample example=new DeptExample();
        example.createCriteria().andDeptIdIsNotNull();


        List<Dept> depts = deptMapper.selectByExample(example);
        log.info("-----------depts"+depts);

        List<DeptDto> dtos=new ArrayList<>();
        depts.forEach(dept -> {
            DeptDto deptDto=new DeptDto();
            BeanUtils.copyProperties(dept,deptDto);
            dtos.add(deptDto);
        });

        List<DeptDto> tree = buildTree(dtos, null);

//        deptDtoList.forEach(deptDto -> {
//            deptDto.setTotal(deptDtoList.size());
//        });

        log.info("-----------depts"+tree.size());
        log.info("-----------depts"+tree);
        return tree;
    }


    public List<DeptDto> buildTree(List<DeptDto> deptDtos, Long pid) {

        List<DeptDto> treeList = new ArrayList<>();
        deptDtos.forEach(deptDto -> {
            if (StringUtils.equals(pid + "", deptDto.getPid() + "")) {
                deptDto.setChildren(buildTree(deptDtos, deptDto.getDeptId()));
                treeList.add(deptDto);
            }
        });
        return treeList;
    }


    @Override
    public DeptDto findById(Long id) {
        Dept dept = deptMapper.selectByPrimaryKey(id);
        DeptDto dto =new DeptDto();
        BeanUtils.copyProperties(dept,dto);
        return dto;
    }

    @Override
    public void create(Dept resources) {

    }

    @Override
    public void update(Dept resources) {

    }

    @Override
    public void delete(Set<DeptDto> deptDtos) {

    }

    @Override
    public List<Dept> findByPid(long pid) {

        DeptExample example=new DeptExample();
        example.createCriteria().andPidIsNull();
        List<Dept> depts = deptMapper.selectByExample(example);

        return depts;
    }

    @Override
    public Set<Dept> findByRoleId(Long id) {

        RolesAndDeptsExample example=new RolesAndDeptsExample();
        example.createCriteria().andRoleIdEqualTo(id);
        List<RolesAndDepts> rolesAndDepts = rolesAndDeptsMapper.selectByExample(example);
        Set set=new HashSet();

        if(rolesAndDepts!=null&&rolesAndDepts.size()>0){


            rolesAndDepts.stream().forEach(rolesAndDept -> {
                Dept dept = deptMapper.selectByPrimaryKey(rolesAndDept.getDeptId());

                set.add(dept);

            });
        }

        return set;
    }

    @Override
    public void download(List<DeptDto> queryAll, HttpServletResponse response) throws IOException {

    }

    @Override
    public Set<DeptDto> getDeleteDepts(List<Dept> deptList, Set<DeptDto> deptDtos) {
        return null;
    }

    @Override
    public List<DeptDto> getSuperior(DeptDto deptDto, List<Dept> depts) {
        return null;
    }

    @Override
    public Object buildTree(List<DeptDto> deptDtos) {
        return null;
    }

    @Override
    public List<Long> getDeptChildren(Long deptId, List<Dept> deptList) {

        DeptExample example=new DeptExample();

        example.createCriteria().andPidIn(deptList.stream().map(Dept::getPid).collect(Collectors.toList()));
        List<Dept> depts = deptMapper.selectByExample(example);
        return depts.stream().map(Dept::getDeptId).collect(Collectors.toList());
    }

    @Override
    public void verification(Set<DeptDto> deptDtos) {

    }
}
