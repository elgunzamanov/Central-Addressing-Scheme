package com.example.controller;

import com.example.dto.CustomerRequestDto;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {
	private final CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("customers", customerService.getAllCustomers());
		return "index";
	}
	
	@GetMapping("/add")
	public String customerAdd(Model model) {
		model.addAttribute("customer", new CustomerRequestDto());
		return "form";
	}
	
	@PostMapping("/save")
	public String addCustomer(@ModelAttribute CustomerRequestDto customer) {
		customerService.saveCustomer(customer);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String customerEdit(@PathVariable Long id, Model model) {
		model.addAttribute("customer", customerService.getCustomer(id));
		return "form";
	}
	
	@GetMapping("/delete/{id}")
	public String customerDelete(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return "redirect:/";
	}
}