package com.example.dto;

import com.example.entity.customer.ContactDetails;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequestDto {
	long id;
	@NotEmpty(message = "PIN code can't be null!")
	@Size(min = 7, max = 7, message = "PIN code must contain 7 symbols!")
	String pin;
	@NotNull(message = "The resident field can't be null!")
	Boolean resident;
	@Valid
	ContactDetails contactDetails;
}