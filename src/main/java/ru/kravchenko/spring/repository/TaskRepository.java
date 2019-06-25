package ru.kravchenko.spring.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import ru.kravchenko.spring.api.ITaskRepository;
import ru.kravchenko.spring.entity.Task;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class TaskRepository implements ITaskRepository {

    @NotNull
    private Map<String, Task> tasksMap = new LinkedHashMap<>();

    @PostConstruct
    private void init() {merge(new Task("DEMO TASK")); }

    @NotNull
    public Collection<Task> findAll() { return tasksMap.values(); }

    @Nullable
    public Task findById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return null;
        return tasksMap.get(id);
    }

    @NotNull
    public Collection<Task> findByIds(@Nullable final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptySet();
        @NotNull final Collection<Task> result = new LinkedHashSet<>();
        for (@Nullable final String id: ids) {
            if (id == null || id.isEmpty()) continue;
            final Task task = findById(id);
            if (task == null) continue;
            result.add(task);
        }
        return  result;
    }

    @Nullable
    public Task merge(@Nullable final Task task) {
        if (task == null) return task;
        @Nullable final String id = task.getId();
        if (id == null || id.isEmpty()) return null;
        tasksMap.put(id, task);
        return task;
    }

    @Nullable
    public Collection<Task> merge(@Nullable final Collection<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) return Collections.emptySet();
        @NotNull final Collection<Task> result = new LinkedHashSet<>();
        for (@Nullable final Task task: tasks) {
            if (task == null) continue;
            result.add(merge(task));
        }
        return result;
    }

    @Override
    public void removeById(@Nullable final String id) {
        if (id == null || id.isEmpty()) return;
        if (!tasksMap.containsKey(id)) return;
        tasksMap.remove(id);
    }

    @Override
    public void removeByIds(@Nullable final Collection<String> ids) {
        if (ids == null || ids.isEmpty()) return;
        for (@Nullable final String id: ids) removeById(id);
    }

    @Override
    public void remove(@Nullable final Collection<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) return;
        for (@Nullable final Task task: tasks) {
            if (task == null) continue;
            removeById(task.getId());
        }
    }

    @Override
    public void removeAll() { tasksMap.clear(); }

}
