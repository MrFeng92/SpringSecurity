package com.fs.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetailService implements UserDetailsService
{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserDetails userDetails = null;
        try
        {
            com.fs.auth.User user = new com.fs.auth.User();
            user.setUsername("");
            user.setPassword("");
            Collection<GrantedAuthority> authList = getAuthorities();
            userDetails = new User(username, user.getPassword().toLowerCase(), true, true, true, true, authList);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取用户的角色权限,为了降低实验的难度，这里去掉了根据用户名获取角色的步骤     * @param    * @return
     */
    private Collection<GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authList;
    }
}
