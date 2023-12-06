package com.mm.moneymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.moneymanagement.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    
}
