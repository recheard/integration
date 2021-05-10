package com.security.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author recheard
 * @description:
 * @date 2021/5/514:24
 */
public class Test1 {

    @Test
    public void test1() {
        System.out.println(new BCryptPasswordEncoder().encode("secret"));
    }


}
