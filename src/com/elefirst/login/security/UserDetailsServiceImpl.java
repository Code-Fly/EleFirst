package com.elefirst.login.security;

import com.elefirst.base.utils.ConfigUtil;
import com.elefirst.base.utils.Const;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;


@Service
@Transactional(rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {

        String adminName = Const.SUPER_USER_NAME;
        String adminPassword = ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_SUPER_USER_PASSWORD);

        Set<GrantedAuthority> grantedAuths = obtionGrantedAuthorities();
        if (loginName.equals(adminName)) {
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();

            User userInfo = new User(adminName, encoder.encodePassword(adminPassword, adminName), grantedAuths);

            return userInfo;
        } else {
            throw new UsernameNotFoundException(new MessageFormat("用户 {0} 不存在").format(new Object[]{loginName}));
        }
    }

    //取得用户的权限
    private Set<GrantedAuthority> obtionGrantedAuthorities() {
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        authSet.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authSet;
    }

    //取得指定角色的权限
    private Set<GrantedAuthority> obtionGrantedAuthorities(String[] roles) {
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        for (String role : roles) {
            authSet.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return authSet;
    }


}