package ru.kravchenko.spring.api.serive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.kravchenko.spring.entity.Task;

import java.util.List;

/**
 * @author Roman Kravchenko
 */

public interface ITaskService {

    @NotNull
    List<Task> findAll();

    @Nullable
    Task findById(@Nullable String id);

    List<Task> findAllTaskByUserId(final String userId);

    @Nullable
    void update(@Nullable Task task);

    @Nullable
    void persist(@Nullable Task task);

    void removeById(@Nullable String id);

    void removeAll();

}
