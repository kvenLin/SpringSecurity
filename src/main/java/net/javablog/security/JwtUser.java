package net.javablog.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


/**
 * 默认密码为空字符串  , 并且设置为启用,没有锁定 . 没有过期.
 */
public class JwtUser implements UserDetails {

    private final String username;
    private final String password;

    public JwtUser(String username) {
//        this.id = id;
        this.username = username;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.email = email;
        this.password = LoginController.loginpwd;
//        this.authorities = authorities;
//        this.enabled = enabled;
//        this.lastPasswordResetDate = lastPasswordResetDate;
    }


    //TODO,可以不进行权限的配置
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //默认有效账户
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //默认账户没有被锁
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //默认凭证有效
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //默认账户可用
    @Override
    public boolean isEnabled() {
        return true;
    }


}
