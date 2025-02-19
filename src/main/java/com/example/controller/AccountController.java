package com.example.controller;

import com.example.dto.AccountRequestDto;
import com.example.entity.account.Id;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {
	private final AccountService accountService;
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping
	public String accounts(Model model) {
		model.addAttribute("accounts", accountService.getAllAccounts());
		return "account";
	}
	
	@GetMapping("/add")
	public String accountAdd(Model model) {
		model.addAttribute("account", new AccountRequestDto());
		return "account_form";
	}
	
	@PostMapping("/save")
	public String addAccount(@ModelAttribute AccountRequestDto account) {
		accountService.saveAccount(account);
		return "redirect:/account";
	}
	
	@GetMapping("/edit/{id}")
	public String accountEdit(@PathVariable Id id, Model model) {
		model.addAttribute("account", accountService.getAccount(id));
		return "account_form";
	}
	
	@GetMapping("/delete/{id}")
	public String accountDelete(@PathVariable Id id) {
		accountService.deleteAccount(id);
		return "redirect:/account";
	}
}