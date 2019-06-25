package java.ru.kravchenko.test;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kravchenko.spring.api.IProjectRepository;
import ru.kravchenko.spring.api.IUserRepository;
import ru.kravchenko.spring.configuration.DataBaseConfig;
import ru.kravchenko.spring.entity.Project;

/**
 * @author Roman Kravchenko
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataBaseConfig.class)
public class ProjectTest {

    private Lorem lorem = new LoremIpsum();

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void insertAnyProject() {
        for (int i = 0; i < 3; i++) insertOne();
    }

    public void insertOne() {
        final Project project = new Project();
        project.setUser(userRepository.findByLogin("Althea"));
        project.setName(lorem.getWords(1));
        project.setDescription(lorem.getWords(5));
        projectRepository.insert(project);
    }

    @Test
    public void findAll() {
        for (Project p: projectRepository.findAll()) System.out.println(p.getName());
    }

    @Test
    public void ids() {
        for (String s: projectRepository.ids()) System.out.println(s);
    }

    @Test
    public void findById() { System.out.println(projectRepository.findById("996be9eb-0375-4c1c-b31b-5c8496e0f27c").getName()); }

    @Test
    public void findAllProjectByUserId() {
        for (Project p: projectRepository.findAllProjectByUserId("f32533aa-0800-4f1c-ab07-243255b7ab22")) System.out.println(p.getName());
    }

    @Test
    public void removeById() {
        projectRepository.removeById("c4790ae1-bbf1-49b8-bf86-515c7843f3b9");
    }

    @Test
    public void removeAllProjectByUserId() {
        projectRepository.removeAllProjectByUserId("0b85de6b-c27d-461c-be7a-e1f111cd915b");
    }

    @Test
    public void update() {
        final Project projectUpdate = projectRepository.findById("09607467-2ac6-475a-9e29-a1d81970cccd");

        projectUpdate.setName(lorem.getWords(1));
        projectUpdate.setDescription(lorem.getWords(3));
        projectRepository.update(projectUpdate);
    }

}
