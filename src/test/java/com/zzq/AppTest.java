package com.zzq;

import static org.junit.Assert.assertTrue;

import com.zzq.model.User;
import com.zzq.service.UserService;
import com.zzq.util.IdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple BootStart.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    @Autowired
    private UserService userService;

    @Test
    public void test() throws Exception {
        User user = new User();
        long id= 5l;
        user.setId(5l);
        user.setUserName("zzq");
        user.setPassWord("zzq");
        System.out.println(userService.save(id,user));
    }

    /*public static void main(String[] args) {
        System.out.println(5%3);
    }*/
}
