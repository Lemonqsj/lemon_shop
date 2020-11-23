package me.lemon.admin.service;

import me.lemon.database.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AdminAuthService {
    /**
     * 根据用户名获取后台管理员
     */
    User getAdminByUsername(String username);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    Map login(String username, String password, HttpServletRequest request);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);





    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);
}
