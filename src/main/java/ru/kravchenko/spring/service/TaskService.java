package ru.kravchenko.spring.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kravchenko.spring.api.jpa.TaskRepository;
import ru.kravchenko.spring.api.jpa.UserRepository;
import ru.kravchenko.spring.api.serive.ITaskService;
import ru.kravchenko.spring.entity.Task;
import ru.kravchenko.spring.entity.User;

import java.util.List;

/**
 * @author Roman Kravchenko
 */

@Service
@Transactional
public class TaskService implements ITaskService {

    @NotNull
    @Autowired
    private TaskRepository taskRepository;

    @NotNull
    @Autowired
    private UserRepository userRepository;

    @Override
    public @NotNull List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public @Nullable Task findById(@Nullable final String id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public List<Task> findAllTaskByUserId(final String userId) {
        final User user = userRepository.findById(userId).get();
        return taskRepository.findByUser(user);
    }

    @Override
    public @Nullable void update(@Nullable final Task task) {
        taskRepository.save(task);
    }

    @Override
    public @Nullable void persist(@Nullable final Task task) {
        taskRepository.save(task);
    }

    @Override
    public void removeById(@Nullable final String id) {
        taskRepository.removeById(id);
    }

    @Override
    public void removeAll() {
        taskRepository.deleteAll();
    }

}
