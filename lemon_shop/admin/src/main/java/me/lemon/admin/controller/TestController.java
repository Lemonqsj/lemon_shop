package me.lemon.admin.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.lemon.admin.dto.LoginUserDto;
import me.lemon.admin.service.AdminAuthService;
import me.lemon.admin.service.OnlineUserService;
import me.lemon.common.common.ResponseResult;
import me.lemon.database.mapper.UserMapper;
import me.lemon.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/admin")
public class TestController {

    @Autowired
    UserMapper userMapper;


    @Value("${jwt.header}")
    private String tokenHeader;
    @Value("${jwt.token-start-with}")
    private String tokenHead;
    @Autowired
    private AdminAuthService adminService;



    @GetMapping("/test")
    public String test(){

        User user = userMapper.selectByPrimaryKey((long) 1);
        log.info("--------"+user.toString());
        return "test";
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(@Validated @RequestBody LoginUserDto umsAdminLoginParam, HttpServletRequest request) {
        Map login = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword(),request);

        String token = (String) login.get("token");
        User user = (User) login.get("user");

        log.info(user.toString());

        if (token == null) {
//            throw new BadRequestException("用户名或密码错误");
            return ResponseResult.validateFailed("用户名或密码错误");
        }


        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("user", JSON.toJSONString(user));
        return ResponseResult.success(tokenMap);
    }
}
