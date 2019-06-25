package java.ru.kravchenko.test;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kravchenko.spring.api.IUserRepository;
import ru.kravchenko.spring.configuration.DataBaseConfig;
import ru.kravchenko.spring.entity.User;

/**
 * @author Roman Kravchenko
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataBaseConfig.class)
public class UserTest {

    private Lorem lorem = new LoremIpsum();

    @Autowired
    private IUserRepository userDAO;

    @Test
    public void ids() {

    }

    @Test
    public void findById() {
        System.out.println(userDAO.findById("3579afee-5586-416a-ac67-fa1750762140").getLogin());
    }

    @Test
    public void findByLogin() {
        System.out.println(userDAO.findByLogin("Ivano").getId());
    }

    @Test
    public void removeById() {
        for (User u: userDAO.findAll()) System.out.println(u.getId());
    }

    @Test
    public void insertAny() {
        for (int i = 0; i < 10; i++) insertOne();
    }

    public void insertOne() {
        final User user = new User();
        user.setLogin(lorem.getFirstName());
        user.setPasswordHash(lorem.getZipCode());
        userDAO.persist(user);
    }

    @Test
    public void removeAll() {
        userDAO.removeAll();
    }

    @Test
    public void loginList() {
        for (String s: userDAO.loginList()) System.out.println(s);
    }

    @Test
    public void loginExist() {
        System.out.println(userDAO.loginExist("met"));
        System.out.println(userDAO.loginExist("s"));
    }

}
