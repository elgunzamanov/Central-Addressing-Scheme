package com.example.repository;

import com.example.entity.account.Account;
import com.example.entity.account.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Id> {
}