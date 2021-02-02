package com.fs;

import com.fs.auth.UserAuth;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SpringSecurityTest
{
    @Test
    void contextLoads()
    {
        PasswordEncoder pe = new BCryptPasswordEncoder();
        String encode = pe.encode("123");
        System.out.println("encode = " + encode);

        boolean matches = pe.matches("123", encode);
        System.out.println("matches = " + matches);
    }
    
    @Test
    void authTest(){
        UserAuth userAuth=new UserAuth();
        String encode = userAuth.encode("123");
        boolean matches = userAuth.matches("123", encode);
        System.out.println("matches = " + matches);


    }
}
