package com.example.controller;

import com.example.dto.CustomerRequestDto;
import com.example.dto.CustomerResponseDto;
import com.example.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
	private final CustomerService customerService;
	
	public CustomerRestController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping
	public ResponseEntity<CustomerResponseDto> registerCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(customerService.saveCustomer(customerRequestDto));
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequestDto customerRequestDto) {
		customerRequestDto.setId(id);
		return ResponseEntity.status(HttpStatus.OK)
			.body(customerService.saveCustomer(customerRequestDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomerResponseDto> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}