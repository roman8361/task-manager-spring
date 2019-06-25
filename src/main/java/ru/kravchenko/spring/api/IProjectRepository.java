package ru.kravchenko.spring.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import ru.kravchenko.spring.entity.Project;

import java.util.Collection;

@Repository
public interface IProjectRepository {

    @NotNull
    Collection<Project> findAll();

    Project findById(@Nullable String id);

    @NotNull
    Collection<Project> findByIds(@Nullable Collection<String> ids);

    @Nullable
    Project merge(@Nullable Project project);

    @Nullable
    Project save(@Nullable Project project);

    @Nullable
    Collection<Project> merge(@Nullable Collection<Project> projects);

    void removeById(@Nullable String id);

    void removeByIds(@Nullable Collection<String> ids);

    void remove(@Nullable Collection<Project> projects);

    void removeAll();

}
