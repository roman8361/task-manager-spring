package ru.kravchenko.spring.dao;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kravchenko.spring.api.IProjectRepository;
import ru.kravchenko.spring.entity.Project;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Roman Kravchenko
 */

@Repository
@Transactional
public class ProjectDAO implements IProjectRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Project> findAll() {
        final List<Project> projects = entityManager.createQuery("SELECT e FROM Project e", Project.class).getResultList();
        return projects;
    }

    @Override
    public List<String> ids() {
        @Nullable final List<String> project = entityManager.createQuery("SELECT e.id FROM Project e", String.class).getResultList();
        return project;

    }

    @Override
    public Project findById(final String id) {
        return entityManager.find(Project.class, id);
    }

    @Override
    public List<Project> findAllProjectByUserId(final String userId) {
        @Nullable final List<Project> projects = entityManager.createQuery("SELECT e FROM Project e WHERE e.user.id =:userId", Project.class)
                .setParameter("userId", userId)
                .getResultList();
        return projects;
    }

    @Override
    public void removeById(final String id) {
        @NotNull final Project project = entityManager.find(Project.class, id);
        entityManager.remove(project);
    }

    @Override
    public void removeAllProjectByUserId(final String userId) {
        @NotNull final List<Project> projects = entityManager.createQuery("SELECT e FROM Project e WHERE e.user.id =:userId", Project.class)
                .setParameter("userId", userId)
                .getResultList();
        for (Project p : projects) entityManager.remove(p);
    }

    @Override
    public void insert(final Project project) {
        entityManager.persist(project);
    }

    @Override
    public void update(final Project project) {
        entityManager.merge(project);
    }

    @Override
    public void clear() {
        @NotNull final List<Project> projects = entityManager.createQuery("SELECT e FROM Project e", Project.class).getResultList();
        for (Project p : projects) entityManager.remove(p);
    }

}
