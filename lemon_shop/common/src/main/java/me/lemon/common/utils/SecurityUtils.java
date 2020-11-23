package me.lemon.common.utils;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.lemon.common.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Slf4j
public class SecurityUtils {

    /**
     *
     * 获取当前登录的用户
     * @return
     */
    public static UserDetails getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication==null){
            throw new BadRequestException(HttpStatus.UNAUTHORIZED,"当前登录状态过期");
        }


        if (authentication.getPrincipal() instanceof UserDetails){


            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            UserDetailsService userDetailsService = SpringContextHolder.getBean(UserDetailsService.class);

            return userDetailsService.loadUserByUsername(userDetails.getUsername());
        }

        throw new BadRequestException(HttpStatus.UNAUTHORIZED,"找不到当前登录的信息");
    }


    /**
     *
     * 获取当前登录用户的id
     * @return
     */
    public static Long getCurrentUserId(){

        UserDetails userDetails = getCurrentUser();


//        log.info("--------UserDetails------"+new JSONObject(userDetails).toString());
//        log.info("--------UserDetails------"+new JSONObject(userDetails).get("user").toString());
//        log.info("--------UserDetails------"+new JSONObject(userDetails).get("userDto").toString());
        return new JSONObject(new JSONObject(new JSONObject(userDetails).get("userDto")).get("user")).get("userId", Long.class);
    }
}
