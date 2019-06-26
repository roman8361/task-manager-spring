package ru.kravchenko.spring.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kravchenko.spring.api.jpa.ProjectRepository;
import ru.kravchenko.spring.api.jpa.UserRepository;
import ru.kravchenko.spring.api.serive.IProjectService;
import ru.kravchenko.spring.entity.Project;
import ru.kravchenko.spring.entity.User;

import java.util.List;

/**
 * @author Roman Kravchenko
 */

@Service
@Transactional
public class ProjectService implements IProjectService {

    @NotNull
    @Autowired
    private ProjectRepository projectRepository;

    @NotNull
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public List<String> ids() {
       return projectRepository.findAllId();
    }

    @Override
    public Project findById(final String id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public List<Project> findAllProjectByUserId(final String userId) {
        final User user = userRepository.findById(userId).get();
        return projectRepository.findByUser(user);
    }

    @Override
    public void removeById(final String id) {
        projectRepository.deleteById(id);
    }

    @Override
    public void removeAllProjectByUserId(final String userId) {
        final User user = userRepository.findById(userId).get();
        final List<Project> userProjectsList = projectRepository.findByUser(user);
        for (Project p : userProjectsList) projectRepository.deleteById(p.getId());
    }

    @Override
    public void insert(final Project project) {
        projectRepository.save(project);
    }

    @Override
    public void update(final Project project) {
        projectRepository.save(project);
    }

    @Override
    public void clear() {
        projectRepository.deleteAll();
    }

}
