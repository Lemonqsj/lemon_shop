package me.lemon.admin.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.lemon.admin.dto.UserDto;
import me.lemon.database.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
public class AdminUserDetails implements UserDetails {



    private UserDto userDto;
    //    private List<UmsResource> resourceList;
    private final List<Long> dataScopes;

    public AdminUserDetails(UserDto userDto, List<Long> dataScopes) {
        this.userDto = userDto;
        this.dataScopes = dataScopes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
//        return resourceList.stream()
//                .map(role ->new SimpleGrantedAuthority(role.getId()+":"+role.getName()))
//                .collect(Collectors.toList());
        return null;
    }


    public User getUmsAdmin() {
        return userDto.getUser();
    }


    @Override
    public String getPassword() {
        return userDto.getUser().getPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getUser().getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        log.info("------------------"+userDto.getUser().getEnabled());
        return userDto.getUser().getEnabled().equals(1)||userDto.getUser().getEnabled()==1;
    }
}
