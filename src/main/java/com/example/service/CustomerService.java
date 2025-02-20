package com.example.service;

import com.example.dto.CustomerRequestDto;
import com.example.dto.CustomerResponseDto;
import com.example.entity.customer.Customer;
import com.example.error.CustomerNotFoundException;
import com.example.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerService {
	private final CustomerRepository customerRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
	}
	
	public CustomerResponseDto saveCustomer(CustomerRequestDto customerRequestDto) {
		Customer customer = customerRepository.findById(customerRequestDto.getId())
			.orElseGet(Customer::new);
		modelMapper.map(customerRequestDto, customer);
		return modelMapper.map(customerRepository.save(customer), CustomerResponseDto.class);
	}
	
	public List<CustomerResponseDto> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return customers.stream()
			.map(customer -> modelMapper.map(customer, CustomerResponseDto.class))
			.toList();
	}
	
	public CustomerRequestDto getCustomer(Long id) throws CustomerNotFoundException {
		return modelMapper.map(customerRepository.findById(id).orElseThrow(() -> {
			log.error("Finding a customer by id {}", id);
			return new CustomerNotFoundException("Customer Not Found 😕");
		}), CustomerRequestDto.class);
	}
	
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}
}