package com.pm.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pm.dao.EmployeeRepository;
import com.pm.entities.Employee;
import com.pm.service.EmployeeService;

@Controller

public class EmployeeController {

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	EmployeeService empservice;

	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@GetMapping
	public String displayEmployee(Model model) {
		logger.info("displayEmployee, STARTED ");
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employees", employees);
		logger.info("displayEmployee, FINISHED ");
		return "listemployee";

	}

	@RequestMapping("/employees")
	public String displayEmployeeForm(Model model) {
		// use moodel to bind obj to views
		logger.info("displayEmployeeForm, STARTED ");
		Employee aEmployee = new Employee();
		model.addAttribute("employee", aEmployee);
		logger.info("displayEmployeeForm, FINISHED ");
		return "newemployee";

	}

	@PostMapping("/save")
	public String createEmployeeForm(Employee employee, Model model, Errors errors) {
		logger.info("createEmployeeForm, STARTED ");

		if (errors.hasErrors()) {
			logger.warn("createEmployeeForm, ERROR Occured:{} ", errors.getAllErrors());
			return "newemployee";
		}

		// save to the database using an employee crud repository

		List<Employee> getall = (List<Employee>) empservice.getall();
		model.addAttribute("employees", getall);
		empservice.save(employee);
		
		logger.info("createEmployeeForm, FINIHSED ");
		return "listemployee";
	}

	@GetMapping("/listemployee")
	public String listEmployee(Model model) {
		logger.info("listEmployee, STARTED ");
		List<Employee> getall = (List<Employee>) empservice.getall();
		model.addAttribute("employees", getall);
		logger.info("listEmployee, FINIHSED ");
		return "listemployee";
	}

	@GetMapping("/update")
	public String displayEmployeeupdateform(@RequestParam("id") long theid, Model model) {
		logger.info("displayEmployeeupdateform, STARTED ");
		Employee theEmp = empservice.findByEmployeeId(theid);

		model.addAttribute("employee", theEmp);
		logger.info("displayEmployeeupdateform, FINIHSED ");
		return "newemployee";

	}

	@GetMapping("/delete")
	public String deleteproduct(@RequestParam("id") long theid, Model model) {
		logger.info("deleteproduct, STARTED ");
		Employee theEmp = empservice.findByEmployeeId(theid);

		empservice.delete(theEmp);
		logger.info("deleteproduct, FINIHSED ");
		return "redirect:/listemployee";

	}

}
