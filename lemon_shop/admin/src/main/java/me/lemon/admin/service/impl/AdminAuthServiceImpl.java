package me.lemon.admin.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.lemon.admin.dto.DeptDto;
import me.lemon.admin.dto.JobDto;
import me.lemon.admin.dto.RoleDto;
import me.lemon.admin.dto.UserDto;
import me.lemon.admin.security.AdminUserDetails;
import me.lemon.admin.service.*;
import me.lemon.common.exception.EntityNotFoundException;
import me.lemon.database.mapper.*;
import me.lemon.database.model.*;
import me.lemon.security.token.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class AdminAuthServiceImpl implements AdminAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminAuthServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;


    @Autowired RolesAndMenusMapper rolesAndMenusMapper;

    @Autowired
    MenuService menuService;

    @Autowired
    private  AuthenticationManagerBuilder authenticationManagerBuilder;


    @Autowired
    RoleService roleService;

    @Autowired
    JobService jobService;
    @Autowired
    DeptService deptService;


    @Autowired
    OnlineUserService onlineUserService;

    @Autowired
    DataService dataService;
    @Override
    public User getAdminByUsername(String username) {
        User admin;
//        UmsAdmin admin = adminCacheService.getAdmin(username);
//        if(admin!=null) return  admin;
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> adminList = userMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
//            adminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public Map login(String username, String password, HttpServletRequest request) {
        String token = null;
        Map<String,Object> result=new HashMap<>();
        //密码需要客户端加密后传递
        try {
            AdminUserDetails userDetails = (AdminUserDetails) loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                log.info("密码不正确");
                throw new UsernameNotFoundException("密码不正确");
            }
            if(!userDetails.isEnabled()){
                log.info("帐号已被禁用");
                throw new UsernameNotFoundException("帐号已被禁用");
            }
            log.info("----------"+userDetails.isEnabled());

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);


            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            final AdminUserDetails jwtUserDto = (AdminUserDetails) authentication.getPrincipal();


            onlineUserService.save(jwtUserDto,token,request);


            result.put("user",userDetails.getUmsAdmin());
            result.put("token",token);
//            updateLoginTimeByUsername(username);
//            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
//            throw new UsernameNotFoundException(e.getMessage(), e);
            throw new EntityNotFoundException(e.getMessage());
        }

        return result;
    }

    @Override
    public String refreshToken(String oldToken) {
        return null;
    }


    static Map<String, AdminUserDetails> userDtoCache = new ConcurrentHashMap<>();
    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        User admin = getAdminByUsername(username);
        if (admin != null) {


            List<RoleDto> byUsersId = roleService.findByUsersId(admin.getUserId());

            List<JobDto> byUserId = jobService.findByUserId(admin.getUserId());

            DeptDto deptDto = deptService.findById(admin.getDeptId());

            UserDto userDto=new UserDto();

            userDto.setUser(admin);
            userDto.setRols(byUsersId);
            userDto.setJobs(byUserId);
            userDto.setDept(deptDto);

            List<Long> deptIds = dataService.getDeptIds(userDto);

            return new AdminUserDetails(userDto,deptIds);


////            List<UmsResource> resourceList = getResourceList(admin.getId());
////            return new AdminUserDetails(admin,resourceList);
//
//            Dept dept = deptMapper.selectByPrimaryKey(admin.getDeptId());
//            log.info("部门信息："+dept.toString());
//
//            UsersAndJobsExample example=new UsersAndJobsExample();
//
//            example.createCriteria().andUserIdEqualTo(admin.getUserId());
//            List<UsersAndJobs> usersAndJobs = usersAndJobsMapper.selectByExample(example);
//
//            List<Job> jobList=new ArrayList<>();
//            if (usersAndJobs!=null&&usersAndJobs.size()>0){
//                for (int i=0;i<usersAndJobs.size();i++){
//                    Job job = jobMapper.selectByPrimaryKey(usersAndJobs.get(i).getJobId());
//
//                    if (job!=null){
//                        jobList.add(job);
//                        log.info("工作信息信息："+job.toString());
//                    }
//                }
//            }

//            UsersAndRolesExample rolesExample=new UsersAndRolesExample();
//            rolesExample.createCriteria().andUserIdEqualTo(admin.getUserId());
//
//            List<UsersAndRoles> usersAndRoles = usersAndRolesMapper.selectByExample(rolesExample);
//
//            List<Role> roleList=new ArrayList<>();
//            if (usersAndRoles!=null&&usersAndRoles.size()>0){
//                for (int i=0;i<usersAndRoles.size();i++){
//                    Role role = roleMapper.selectByPrimaryKey(usersAndRoles.get(i).getRoleId());
//
//                    if (role!=null){
//                      roleList.add(role);
//                        log.info("角色信息信息："+role.toString());
//                    }
//                }
//            }


//            List<Menu> menuByRoleIdsAndType = menuMapper.findMenuByRoleIdsAndType(roleList.stream().map(Role::getRoleId).collect(Collectors.toList()), 2);

//            List<MenuDto> menuDtoList = menuService.findByUser(admin.getUserId());


            //role 与 menu相关
//
//            RolesAndMenusExample rolesAndMenusExample =new RolesAndMenusExample();
//            rolesAndMenusExample.createCriteria().andRoleIdEqualTo(usersAndRoles.get(0).getRoleId());
//            List<RolesAndMenus> rolesAndMenus = rolesAndMenusMapper.selectByExample(rolesAndMenusExample);
//
//            List<Menu> menuList=new ArrayList<>();
//            List<MenuDto> menuDtos=new ArrayList<>();
//            if (rolesAndMenus!=null&&rolesAndMenus.size()>0){
//                for (int i=0;i<rolesAndMenus.size();i++){
//
//                    Menu menu = menuMapper.selectByPrimaryKey(rolesAndMenus.get(i).getMenuId());
//
//
//                    if (menu!=null){
//                        menuList.add(menu);
//                        MenuDto dto=new MenuDto();
////                        dto.setMenu(menu);
//                        BeanUtils.copyProperties(menu,dto);
//
//                        menuDtos.add(dto);
//                        log.info("菜单信息信息："+dto.toString());
//                    }
//                }
//            }



//            AuthUserDetailDto detailDto=new AuthUserDetailDto();
//            detailDto.setDept(dept);
//            detailDto.setUser(admin);
//            detailDto.setJobs(jobList);
//            detailDto.setRols(roleList);


//            log.info(detailDto.toString());

//            log.info("菜单个数："+menuList.size());


//            List<MenuDto> menuDtos1 = buildTree(menuDtos);

//
//            List<MenuDto> menuDtos2 = buildMenuTree(menuDtos, null);
//
//
//
//            log.info(JSON.toJSONString(menuDtos2));
//

//            return new AdminUserDetails(admin);
        }

//        log.info("用户名错误");
//        throw new EntityNotFoundException(User.class,"username",username);
        throw new EntityNotFoundException("用户名错误");

    }






}
