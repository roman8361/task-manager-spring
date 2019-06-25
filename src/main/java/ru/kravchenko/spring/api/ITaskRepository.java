package ru.kravchenko.spring.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import ru.kravchenko.spring.entity.Task;

import java.util.Collection;

@Repository
public interface ITaskRepository {

    @NotNull
    Collection<Task> findAll();

    @Nullable
    Task findById(@Nullable String id);

    @NotNull
    Collection<Task> findByIds(@Nullable Collection<String> ids);

    @Nullable
    Task merge(@Nullable Task project);

    @Nullable
    Collection<Task> merge(@Nullable Collection<Task> tasks);

    void removeById(@Nullable String id);

    void removeByIds(@Nullable Collection<String> ids);

    void remove(@Nullable Collection<Task> tasks);

    void removeAll();

}
