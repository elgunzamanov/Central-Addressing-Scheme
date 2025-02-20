package com.example.entity.customer;

import jakarta.persistence.Embeddable;
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
@Embeddable
public class ContactDetails {
	@NotNull(message = "Mobile number can't be null!")
	@Size(min = 13, max = 13, message = "Mobile number must contain 13 symbols(f.e: +994509998877)!")
	String mobileNumber;
}