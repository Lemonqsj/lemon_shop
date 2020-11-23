package me.lemon.admin.service;


import lombok.extern.slf4j.Slf4j;
import me.lemon.admin.dto.OnlineUserDto;
import me.lemon.admin.security.AdminUserDetails;
import me.lemon.common.utils.PageUtil;
import me.lemon.common.utils.RedisUtils;
import me.lemon.common.utils.StringUtils;

import me.lemon.security.config.bena.SecurityProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Slf4j
public class OnlineUserService {


    private final SecurityProperties properties;
    private final RedisUtils redisUtils;

    public OnlineUserService(SecurityProperties properties, RedisUtils redisUtils) {
        this.properties = properties;
        this.redisUtils = redisUtils;
    }


    /**
     * 保存在线用户信息
     *
     * @param /
     * @param token   /
     * @param request /
     */
    public void save(AdminUserDetails adminUserDetails, String token, HttpServletRequest request) {
        String dept = adminUserDetails.getUserDto().getDept().getName();
        String ip = StringUtils.getIp(request);
        String browser = StringUtils.getBrowser(request);
        String address = StringUtils.getCityInfo(ip);
        OnlineUserDto onlineUserDto = null;
        try {
            onlineUserDto = new OnlineUserDto(adminUserDetails.getUsername(), adminUserDetails.getUserDto().getUser().getNickName(), dept, browser, ip, address, token, new Date(),0);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        redisUtils.set(properties.getOnlineKey() + token, onlineUserDto, properties.getTokenValidityInSeconds() / 1000);
    }


    public List getAll(Pageable pageable) {
        List<OnlineUserDto> onlineUserDtos = getAll();
        log.info("--------获取在线用户-----" + onlineUserDtos.toString());
        log.info("--------获取在线用户--getPageNumber---" + pageable.getPageNumber() + "------getPageSize--" + pageable.getPageSize());

        return PageUtil.toPage(pageable.getPageNumber() - 1, pageable.getPageSize(), onlineUserDtos);
//        return PageUtil.toPage(PageUtil.toPage(pageable.getPageNumber(), pageable.getPageSize(), onlineUserDtos), onlineUserDtos.size());
    }

    public List<OnlineUserDto> getAll() {
        List<String> keys = redisUtils.scan(properties.getOnlineKey() + "*");
        log.info("------------匹配到的key----" + keys.size());

        Collections.reverse(keys);
        List<OnlineUserDto> onlineUserDtos = new ArrayList<>();

        keys.forEach(key -> {
            OnlineUserDto onlineUserDto = (OnlineUserDto) redisUtils.get(key);
            onlineUserDto.setTotal(keys.size());

            onlineUserDtos.add(onlineUserDto);
        });
//        onlineUserDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        log.info("------redis中获取-到的数量为---" + onlineUserDtos.size());
        return onlineUserDtos;
    }

    /**
     * 根据用户名强退用户
     * @param username /
     */
    @Async
    public void kickOutForUsername(String username) {
        List<OnlineUserDto> onlineUsers = getAll();
        for (OnlineUserDto onlineUser : onlineUsers) {
            if (onlineUser.getUserName().equals(username)) {
                kickOut(onlineUser.getKey());
            }
        }
    }


    /**
     * 踢出用户
     * @param key /
     */
    public void kickOut(String key){
        key = properties.getOnlineKey() + key;
        redisUtils.del(key);
    }
}
