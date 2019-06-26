package ru.kravchenko.test;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kravchenko.spring.configuration.DataBaseConfig;
import ru.kravchenko.spring.entity.User;
import ru.kravchenko.spring.service.UserService;

/**
 * @author Roman Kravchenko
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataBaseConfig.class)
public class UserJPATest {

    private Lorem lorem = new LoremIpsum();

    @Autowired
    private UserService userRepositoryDAO;

    @Test
    public void persist() {
        final User user = new User();
        user.setLogin(lorem.getFirstName());
        user.setPasswordHash(lorem.getZipCode());
        userRepositoryDAO.persist(user);
    }

    @Test
    public void findById() {
        System.out.println(userRepositoryDAO.findById("ab092040-c3a9-4783-a5f1-d668750f67b0").getLogin());
    }

    @Test
    public void findByLogin() {
        System.out.println(userRepositoryDAO.findByLogin("444").getId());
    }

    @Test
    public void loginExist() {
        System.out.println(userRepositoryDAO.loginExist("44"));
    }

    @Test
    public void checkLoginPassword() {
        System.out.println(userRepositoryDAO.checkLoginPassword("11", "111"));
    }

    @Test
    public void merge() {
        User user = userRepositoryDAO.findById("ab092040-c3a9-4783-a5f1-d668750f67b0");
        user.setLogin("555");
        user.setPasswordHash("555");
        userRepositoryDAO.merge(user);
    }


}
