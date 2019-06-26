package ru.kravchenko.test;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kravchenko.spring.api.serive.IProjectService;
import ru.kravchenko.spring.api.serive.ITaskService;
import ru.kravchenko.spring.api.serive.IUserService;
import ru.kravchenko.spring.configuration.DataBaseConfig;
import ru.kravchenko.spring.entity.Task;

/**
 * @author Roman Kravchenko
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataBaseConfig.class)
public class TaskJPATest {

    private Lorem lorem = new LoremIpsum();

    @Autowired
    private IProjectService projectRepository;

    @Autowired
    private IUserService userRepository;

    @Autowired
    private ITaskService taskRepository;

    @Test
    public void insertOneTask() {
        final Task task  = new Task();
        task.setProject(projectRepository.findById("57d29e86-f404-4694-ab85-3db81058c782"));
        task.setUser(projectRepository.findById("57d29e86-f404-4694-ab85-3db81058c782").getUser());
        task.setName(lorem.getWords(1));
        task.setDescription(lorem.getWords(5));
        taskRepository.persist(task);
    }

    @Test
    public void findAll() {
        for (Task t: taskRepository.findAll()) System.out.println(t.getName());
    }

    @Test
    public void findAllTaskByUserId() {
        for (Task t: taskRepository.findAllTaskByUserId("ab092040-c3a9-4783-a5f1-d668750f67b0")) {
            System.out.println(t.getName());
        }
    }

    @Test
    public void removeById() {
        taskRepository.removeById("95720ef3-f72d-4ee3-857b-a27d133f6cc3");
    }

}
