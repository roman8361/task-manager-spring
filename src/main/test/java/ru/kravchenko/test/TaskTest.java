package java.ru.kravchenko.test;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kravchenko.spring.api.IProjectRepository;
import ru.kravchenko.spring.api.ITaskRepository;
import ru.kravchenko.spring.api.IUserRepository;
import ru.kravchenko.spring.configuration.DataBaseConfig;
import ru.kravchenko.spring.entity.Task;

/**
 * @author Roman Kravchenko
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataBaseConfig.class)
public class TaskTest {

    private Lorem lorem = new LoremIpsum();

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Test
    public void insertAnyTask() {
        for (int i = 0; i < 3; i++) insertOneTask();
    }

    @Test
    public void insertOneTask() {
        final Task task  = new Task();

        task.setProject(projectRepository.findById("31632cc8-612f-4368-a99e-d59336783e4e"));
        task.setUser(projectRepository.findById("31632cc8-612f-4368-a99e-d59336783e4e").getUser());
        task.setName(lorem.getWords(1));
        task.setDescription(lorem.getWords(5));
        taskRepository.persist(task);
    }

    @Test
    public void findAll() {
        for (Task t: taskRepository.findAll()) System.out.println(t.getName());
    }

    @Test
    public void findById() {
        System.out.println(taskRepository.findById("2b902743-2731-415e-9cf3-57d72211385b").getName());
    }

    @Test
    public void findAllTaskByUserId() {
        for (Task t: taskRepository.findAllTaskByUserId("0129045a-23fa-4bd2-bc92-a72127aaf992")) {
            System.out.println(t.getName());
        }
    }

    @Test
    public void update() {
        final Task currentTask = taskRepository.findById("03d357b6-1065-4a0e-9c41-baad6fea91fa");
        assert currentTask != null;
        currentTask.setName("#####@@@@@");
        taskRepository.update(currentTask);
    }

    @Test
    public void removeById() {
        taskRepository.removeById("2b902743-2731-415e-9cf3-57d72211385b");
    }

    @Test
    public void removeAll() {
        taskRepository.removeAll();
    }

}
