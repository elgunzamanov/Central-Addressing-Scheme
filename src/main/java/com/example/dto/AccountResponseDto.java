package com.example.dto;

import com.example.entity.account.Id;
import com.example.entity.account.Servicer;
import com.example.enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponseDto {
	Id id;
	@Enumerated(EnumType.STRING)
	AccountType type;
	String currency;
	Servicer servicer;
	LocalDate openingDate;
	LocalDate closingDate;
}