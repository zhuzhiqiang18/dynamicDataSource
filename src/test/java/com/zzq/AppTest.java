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
    public void save() throws Exception {
        for (int i=0;i<10;i++){
            User user = new User();
            long id= IdUtil.getId();
            user.setId(id);
            user.setUserName("zzq"+i);
            user.setPassWord("zzq"+i);
            System.out.println(userService.save(id,user));
        }

    }
    @Test
    public void update() throws Exception {
        User user = new User();
        long id= 19061298447915L;
        user.setId(id);
        user.setUserName("zzqzzq");
        user.setPassWord("zzqzzq");
        System.out.println(userService.update(id,user));
    }

    @Test
    public void delete() throws Exception {
        User user = new User();
        long id= 19061298766897L;
        System.out.println(userService.delete(id));
    }

    @Test
    public void select() throws Exception {
        User user = new User();
        long id= 3L;
        System.out.println(userService.select(id).toString());
    }

    @Test
    public void updateNam() throws Exception {
        User user = new User();
        long id= 3L;
        System.out.println(userService.updateUser(3L,"123","123"));
    }

}
