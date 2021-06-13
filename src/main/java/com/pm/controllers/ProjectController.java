package com.pm.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pm.entities.Project;
import com.pm.service.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectService proRep;
	private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@GetMapping
	public String displayProjects(Model model) {
		logger.info("displayProjects, STARTED ");
		List<Project> projects = proRep.getAll();
		model.addAttribute("projects", projects);
		logger.info("displayProjects, FINISHED ");
		return "listproject";

	}

	@RequestMapping("/new")
	public String displayProjectForm(Model model) {
		logger.info("displayProjectForm, STARTED ");
		// use moodel to bind obj to views
		Project aproject = new Project();
		// Iterable<Employee> employees = pro.getall();

		model.addAttribute("project", aproject);

		// model.addAttribute("allEmployees", employees);
		logger.info("displayProjectForm, FINISHED ");
		return "newproject";

	}

	@RequestMapping("/save")
	public String createProjectForm(Project project, Model model) {
		// method to save data
		logger.info("createProjectForm, STARTED ");
		proRep.save(project);
		List<Project> getall = (List<Project>) proRep.getAll();
		model.addAttribute("projects", getall);
		logger.info("createProjectForm, FINISHED ");
		return "listproject";

	}
}
