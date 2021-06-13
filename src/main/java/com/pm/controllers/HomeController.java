package com.pm.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pm.dao.EmployeeRepository;
import com.pm.dao.ProjectRepository;
import com.pm.dto.EmployeeProject;
import com.pm.entities.Project;
import com.pm.service.EmployeeService;

@Controller

public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	ProjectRepository proRepo;

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	EmployeeService empService;

	@GetMapping("/")
	public String displayhome(Model model) {
		logger.info("displayHome, STARTED ");

		List<Project> projects = proRepo.findAll();

		model.addAttribute("projects", projects);

		List<EmployeeProject> employeesProjectCnt = empService.employeeProjects();
		model.addAttribute("employeeListProjectCnt", employeesProjectCnt);

		logger.info("displayHome, FINISHED ");
		return "home";
	}
}
