package com.sda.project.controller.project;

import com.sda.project.model.Project;
import com.sda.project.service.ProjectService;
import com.sda.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {
    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/projects")
    public String showProjectsPage(Model model) {
        model.addAttribute("projects", projectService.findAll());
        return "project/projects";
    }

    @GetMapping("/projects/add")
    public String showAddForm(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("users", userService.findAll());
        return "project/project-add";
    }

