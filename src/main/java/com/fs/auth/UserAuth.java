package com.fs.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserAuth implements PasswordEncoder
{
    /**
     * 数据库用户注册，对密码加密
     * @param charSequence
     * @return
     */
    @Override
    public String encode(CharSequence charSequence)
    {
        return new BCryptPasswordEncoder().encode(charSequence);
    }

    /**
     *
     * @param charSequence 用户输入原始密码
     * @param s 加密之后的密码
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String s)
    {
        return new BCryptPasswordEncoder().matches(charSequence,s);
    }
}
