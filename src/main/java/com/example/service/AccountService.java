package com.example.service;

import com.example.dto.AccountRequestDto;
import com.example.dto.AccountResponseDto;
import com.example.dto.CustomerRequestDto;
import com.example.entity.account.Account;
import com.example.entity.account.Id;
import com.example.repository.AccountRepository;
import org.iban4j.Iban;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
	private final AccountRepository accountRepository;
	private final ModelMapper modelMapper;
	
	public AccountService(AccountRepository accountRepository, ModelMapper modelMapper) {
		this.accountRepository = accountRepository;
		this.modelMapper = modelMapper;
	}
	
	public AccountResponseDto saveAccount(AccountRequestDto accountRequestDto) {
		Account account = accountRepository.findById(accountRequestDto.getId())
			.orElseGet(() -> {
				accountRequestDto.setId(new Id(Iban.random().toString()));
				return modelMapper.map(accountRequestDto, Account.class);
			});
		return modelMapper.map(accountRepository.save(account), AccountResponseDto.class);
	}
	
	public List<AccountResponseDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream()
			.map(account -> modelMapper.map(account, AccountResponseDto.class))
			.toList();
	}
	
	public AccountRequestDto getAccount(Id id) {
		return modelMapper.map(accountRepository.findById(id).orElseThrow(), AccountRequestDto.class);
	}
	
	public void deleteAccount(Id id) {
		accountRepository.deleteById(id);
	}
}