package me.lemon.admin.service.impl;


import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import me.lemon.admin.dto.JobDto;
import me.lemon.admin.dto.RoleDto;
import me.lemon.admin.dto.LoginUserDto;
import me.lemon.admin.dto.UserDto;
import me.lemon.admin.service.JobService;
import me.lemon.admin.service.OnlineUserService;
import me.lemon.admin.service.RoleService;
import me.lemon.admin.service.UserService;
import me.lemon.common.exception.EntityExistException;
import me.lemon.common.utils.ValidationUtil;
import me.lemon.database.mapper.UserMapper;
import me.lemon.database.model.User;
import me.lemon.database.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleService roleService;

    @Autowired
    JobService jobService;


    @Autowired
    OnlineUserService onlineUserService;


    @Override
    public UserDto findById(long id) {
        UserDto dto = new UserDto();
        User user = userMapper.selectByPrimaryKey(id);
        dto.setJobs(jobService.findByUserId(user.getUserId()));
        dto.setRols(roleService.findByUsersId(user.getUserId()));
        dto.setUser(user);

        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(User resources) {
        UserExample example=new UserExample();
        example.createCriteria().andUsernameEqualTo(resources.getUsername());
        List<User> users = userMapper.selectByExample(example);

        if (users!=null||users.size()>0){
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }


        UserExample em=new UserExample();
        em.createCriteria().andEmailEqualTo(resources.getEmail());
        List<User> users1 = userMapper.selectByExample(em);

        if (users1!=null||users1.size()>0){
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }


        userMapper.insert(resources);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User resources) {

        User user = userMapper.selectByPrimaryKey(resources.getUserId());
        ValidationUtil.isNull(user.getUserId(),"user","id",resources.getUserId());


        UserExample example=new UserExample();
        example.createCriteria().andUsernameNotEqualTo(resources.getUsername());
        List<User> users = userMapper.selectByExample(example);

        UserExample example2=new UserExample();
        example.createCriteria().andEmailEqualTo(resources.getEmail());
        List<User> users2 = userMapper.selectByExample(example2);

        // 如果用户被禁用，则清除用户登录信息
        if(resources.getEnabled()==0){
            onlineUserService.kickOutForUsername(resources.getUsername());
        }
        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setEnabled(resources.getEnabled());
        user.setPhone(resources.getPhone());
        user.setNickName(resources.getNickName());
        user.setGender(resources.getGender());
       userMapper.updateByPrimaryKey(user);



    }

    @Override
    public void delete(List<Long> ids) {


        UserExample example=new UserExample();
        example.createCriteria().andUserIdIn(ids);

        int i = userMapper.deleteByExample(example);

        log.info("删除了用户，用户id为："+ids+"------删除了几个用户："+i);
    }

    @Override
    public UserDto findByName(String userName) {
        return null;
    }

    @Override
    public void updatePass(String username, String encryptPassword) {

    }

    @Override
    public Map<String, String> updateAvatar(MultipartFile file) {
        return null;
    }

    @Override
    public void updateEmail(String username, String email) {

    }

    @Override
    public List<UserDto> queryAll(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdIsNotNull();
        List<User> users = userMapper.selectByExample(userExample);
        int total = userMapper.countByExample(userExample);
        List<UserDto> list = new ArrayList<>();
        users.forEach(user -> {
            UserDto dto = new UserDto();
            List<JobDto> jobDtos = jobService.findByUserId(user.getUserId());
            List<RoleDto> roleDtos = roleService.findByUsersId(user.getUserId());
            dto.setUser(user);
            dto.setJobs(jobDtos);
            dto.setRols(roleDtos);
            dto.setTotal(total);
            list.add(dto);
        });


        return list;
    }

    //查询所有用户，不分页
    @Override
    public List<UserDto> queryAll() {

//        PageHelper.startPage(1,3);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdIsNotNull();
        List<User> users = userMapper.selectByExample(userExample);


//        Stream.of(users).map(User::getUserId).collect(Collectors.toList());
//        roleService.findByUsersId()
        List<UserDto> list = new ArrayList<>();
        users.forEach(user -> {
            UserDto dto = new UserDto();
            List<JobDto> jobDtos = jobService.findByUserId(user.getUserId());
            List<RoleDto> roleDtos = roleService.findByUsersId(user.getUserId());
            dto.setUser(user);
            dto.setJobs(jobDtos);
            dto.setRols(roleDtos);
            list.add(dto);
        });


        return list;
    }

    @Override
    public void download(List<UserDto> queryAll, HttpServletResponse response) throws IOException {

    }

    /**
     * 用户自主修改资料
     *
     * @param resources /
     */
    @Override
    public void updateCenter(User resources) {
        UserExample example = new UserExample();

        example.createCriteria().andUserIdEqualTo(resources.getUserId());

        int i = userMapper.updateByExample(resources, example);


    }
}
