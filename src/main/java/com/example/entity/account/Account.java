package com.example.entity.account;

import com.example.enums.AccountType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "accounts")
public class Account {
	@EmbeddedId
	Id id;
	@Enumerated(EnumType.STRING)
	AccountType type;
	String currency;
	@Embedded
	Servicer servicer;
	LocalDate openingDate;
	LocalDate closingDate;
}