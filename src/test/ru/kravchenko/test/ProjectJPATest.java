package ru.kravchenko.test;


import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kravchenko.spring.api.serive.IProjectService;
import ru.kravchenko.spring.api.serive.IUserService;
import ru.kravchenko.spring.configuration.DataBaseConfig;
import ru.kravchenko.spring.entity.Project;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataBaseConfig.class)
public class ProjectJPATest {

    private Lorem lorem = new LoremIpsum();

    @Autowired
    private IUserService userRepository;

    @Autowired
    private IProjectService projectRepository;

    @Test
    public void insert() {
        final Project project = new Project();
        project.setDescription(lorem.getWords(4));
        project.setName(lorem.getWords(1));
        project.setUser(userRepository.findByLogin("Clair"));
        projectRepository.insert(project);
    }

    @Test
    public void findById() {
        System.out.println(projectRepository.findById("edbc7674-9281-4028-bd96-51cbf52362dd").getName());
    }

    @Test
    public void findAllProjectByUserId() {
        for(Project p: projectRepository.findAllProjectByUserId("9d5f64bc-c24f-4eb5-8e07-b9d316396a49"))
            System.out.println(p.getName());
    }

    @Test
    public void removeById() {
      projectRepository.removeById("67853a89-dfd1-4b16-a19f-f1db5cc5acdc");
    }

}
