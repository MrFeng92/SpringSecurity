package com.fs.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    //授权，链式编程
    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        //首页所有人可以访问，功能页面只有对应权限的用户可以访问
        //请求授权的规则，有对应角色的用户才可以访问对应的页面
        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/level1/**").hasRole("vip1").
                antMatchers("/level2/**").hasRole("vip2").
                antMatchers("/level3/**").hasRole("vip3");

        //没有权限会跳转到登录页面
        //自定义登录页，和登录参数
        http.formLogin().loginPage("/toLogin").
         usernameParameter("user").passwordParameter("pwd").
        loginProcessingUrl("/login");

        http.csrf().disable();//关闭防止网站攻击
        //注销
        http.logout().logoutSuccessUrl("/");
        //开启记住我功能，通过浏览器保存cookie是实现
        http.rememberMe().rememberMeParameter("remember");
    }

    //认证
    //     * PasswordEncoder 密码需要编码
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        //认证内存中的用户，正常是从数据库中获取
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").password(new BCryptPasswordEncoder().encode("123")).roles("vip2", "vip3").and().withUser("liyan").password(new BCryptPasswordEncoder().encode("123")).roles("vip1").and().withUser("root").password(new BCryptPasswordEncoder().encode("123")).roles("vip1", "vip2", "vip3");
    }
}
