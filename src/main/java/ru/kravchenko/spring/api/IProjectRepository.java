package ru.kravchenko.spring.api;

import ru.kravchenko.spring.entity.Project;

import java.util.List;

/**
 * @author Roman Kravchenko
 */

public interface IProjectRepository {

    List<Project> findAll();

    List<String> ids();

    Project findById(final String id);

    List<Project> findAllProjectByUserId(final String userId);

    void removeById(final String id);

    void removeAllProjectByUserId(final String userId);

    void insert(final Project project);

    void update(final Project project);

    void clear();

}
