package com.example.controller;

import com.example.dto.AccountRequestDto;
import com.example.dto.AccountResponseDto;
import com.example.entity.account.Id;
import com.example.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {
	private final AccountService accountService;
	
	public AccountRestController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping
	public ResponseEntity<AccountResponseDto> registerAccount(
		@RequestBody AccountRequestDto accountRequestDto
	) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(accountService.saveAccount(accountRequestDto));
	}
	
	@GetMapping
	public ResponseEntity<List<AccountResponseDto>> getAllAccounts() {
		return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccounts());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AccountResponseDto> updateAccount(
		@PathVariable Id id, @RequestBody AccountRequestDto accountRequestDto
	) {
		accountRequestDto.setId(id);
		return ResponseEntity.status(HttpStatus.OK)
			.body(accountService.saveAccount(accountRequestDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<AccountResponseDto> deleteAccount(@PathVariable Id id) {
		accountService.deleteAccount(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}