package ru.kravchenko.spring.controler;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kravchenko.spring.api.serive.IProjectService;
import ru.kravchenko.spring.api.serive.ISessionService;
import ru.kravchenko.spring.constant.FieldConst;
import ru.kravchenko.spring.entity.Project;
import ru.kravchenko.spring.entity.User;
import ru.kravchenko.spring.exception.AuthenticationException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Roman Kravchenko
 */

@Controller
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @NotNull
    @Autowired
    private ISessionService sessionService;

    @GetMapping("project-list")
    public String projectList(final Model model,
                              final HttpSession session,
                              @NotNull final HttpServletResponse resp) throws IOException {
        try {
            if (session.getAttribute(FieldConst.USER) == null) return "redirect:/sessionNotFound";
            sessionService.validateSession(session);
            final User currentUser = (User) session.getAttribute(FieldConst.USER);
            System.out.println("ID CURRENT USER: " + currentUser.getId());
            final Iterable<Project> projects = projectService.findAllProjectByUserId(currentUser.getId());
            model.addAttribute("projects", projects);

        } catch (AuthenticationException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
        return "project-list";
    }

    @GetMapping("project-create")
    public String projectCreate(final HttpSession session) {
        if (session.getAttribute(FieldConst.USER) == null) return "redirect:/sessionNotFound";
        final Project project = new Project();
        final User currentUser = (User) session.getAttribute(FieldConst.USER);
        project.setName("New Project");
        project.setDescription("");
        project.setUser(currentUser);
        projectService.insert(project);
        return "redirect:/project-list";
    }

    @GetMapping("/project-edit/{id}")
    public String projectEdit(final Model model,
                              @PathVariable("id") final String id,
                              final HttpSession session) {
        if (session.getAttribute(FieldConst.USER) == null) return "redirect:/sessionNotFound";
        final Project project = projectService.findById(id);
        model.addAttribute("project", project);
        return "project-edit";
    }

    @GetMapping("/project-view/{id}")
    public String projectView(final Model model,
                              @PathVariable("id") final String id,
                              final HttpSession session) {
        if (session.getAttribute(FieldConst.USER) == null) return "redirect:/sessionNotFound";
        final Project project = projectService.findById(id);
        model.addAttribute("project", project);
        return "project-view";
    }

    @GetMapping("/project-delete/{id}")
    public String projectDelete(@PathVariable("id") final String id, final HttpSession session) {
        if (session.getAttribute(FieldConst.USER) == null) return "redirect:/sessionNotFound";
        System.out.println(id);
        projectService.removeById(id);
        return "redirect:/project-list";
    }

    @PostMapping("project-save")
    public String projectSave(@ModelAttribute("project") final Project project,
                              final BindingResult result,
                              final HttpSession session) {

        if (session.getAttribute(FieldConst.USER) == null) return "redirect:/sessionNotFound";
        final User currentUser = (User) session.getAttribute(FieldConst.USER);
        project.setUser(currentUser);
        if (!result.hasErrors()) projectService.update(project);
        return "redirect:/project-list";
    }

}
