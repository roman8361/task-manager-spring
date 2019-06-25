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
import ru.kravchenko.spring.api.ISessionService;
import ru.kravchenko.spring.api.ITaskRepository;
import ru.kravchenko.spring.constant.FieldConst;
import ru.kravchenko.spring.entity.Task;
import ru.kravchenko.spring.exception.AuthenticationException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Roman Kravchenko
 */

@Controller
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @NotNull
    @Autowired
    private ISessionService sessionService;

    @GetMapping("task-list")
    public String taskList(final Model model,
                              final HttpSession session,
                              @NotNull final HttpServletResponse resp) throws IOException {
        try {
            if (session.getAttribute(FieldConst.USER) == null) return "redirect:/sessionNotFound";
            sessionService.validateSession(session);
            final Iterable<Task> tasks = taskRepository.findAll();
            model.addAttribute("tasks", tasks);
            for (Task t : taskRepository.findAll()) System.out.println(t.getId()); //TODO dell later
        } catch (AuthenticationException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
        return "task-list";
    }

    @GetMapping("task-create")
    public String taskCreate(final HttpSession session) {
        if (session.getAttribute(FieldConst.USER) == null) return "redirect:/sessionNotFound";
        final Task task = new Task();
        task.setName("New TASK");
        task.setDescription("");
        taskRepository.merge(task);
        return "redirect:/task-list";
    }

    @GetMapping("task-edit/{id}")
    public String taskEdit(final Model model,
                              @PathVariable("id") final String id,
                              final HttpSession session) {
        if (session.getAttribute(FieldConst.USER) == null) return "redirect:/sessionNotFound";
        final Task task = taskRepository.findById(id);
        model.addAttribute("task", task);
        return "task-edit";
    }

    @GetMapping("/task-view/{id}")
    public String taskView(final Model model,
                              @PathVariable("id") final String id,
                              final HttpSession session) {
        if (session.getAttribute(FieldConst.USER) == null) return "redirect:/sessionNotFound";
        final Task task = taskRepository.findById(id);
        model.addAttribute("task", task);
        return "task-view";
    }

    @GetMapping("/task-delete/{id}")
    public String taskDelete(@PathVariable("id") final String id, final HttpSession session) {
        if (session.getAttribute(FieldConst.USER) == null) return "redirect:/sessionNotFound";
        System.out.println(id);
        taskRepository.removeById(id);
        return "redirect:/task-list";
    }

    @PostMapping("task-save")
    public String taskSave(@ModelAttribute("task") final Task task,
                              final BindingResult result,
                              final HttpSession session) {
        if (session.getAttribute(FieldConst.USER) == null) return "redirect:/sessionNotFound";
        if (!result.hasErrors()) taskRepository.merge(task);
        for (Task t : taskRepository.findAll()) System.out.println(t.getId()); //TODO dell later

        return "redirect:/task-list";
    }

}
