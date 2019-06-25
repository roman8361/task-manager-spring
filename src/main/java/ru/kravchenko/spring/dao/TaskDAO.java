package ru.kravchenko.spring.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kravchenko.spring.api.ITaskRepository;
import ru.kravchenko.spring.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Roman Kravchenko
 */

@Repository
@Transactional
public class TaskDAO implements ITaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public @Nullable List<Task> findAll() {
        @Nullable final List<Task> task = entityManager.createQuery("SELECT e FROM Task e", Task.class).getResultList();
        return task;
    }

    @Override
    public @Nullable Task findById(@Nullable final String id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> findAllTaskByUserId(String userId) {
        @Nullable final List<Task> tasks = entityManager.createQuery("SELECT e FROM Task e WHERE e.user.id =:userId", Task.class)
                .setParameter("userId", userId)
                .getResultList();
        return tasks;
    }

    @Nullable
    public void update(@Nullable final Task task) {
        entityManager.merge(task);
    }

    @Nullable
    public void persist(@Nullable final Task task) {
        entityManager.persist(task);
    }

    @Override
    public void removeById(@Nullable final String id) {
        @NotNull final Task task = entityManager.find(Task.class, id);
        entityManager.remove(task);
    }

    @Override
    public void removeAll() {
        @Nullable final List<Task> tasks = entityManager.createQuery("SELECT e FROM Task e", Task.class).getResultList();
        assert tasks != null;
        for (Task t : tasks) entityManager.remove(t);
    }

}
