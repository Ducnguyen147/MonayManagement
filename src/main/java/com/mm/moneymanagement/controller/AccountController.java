package com.mm.moneymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.mm.moneymanagement.model.Account;
import com.mm.moneymanagement.repository.AccountRepository;
import com.mm.moneymanagement.exception.ResourceNotFoundException;
@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/account")
    Account newAccount(@RequestBody Account newAccount) {
        return accountRepository.save(newAccount);
    }

    @GetMapping("/account/{id}")
    Account getAccountById(@PathVariable Long id) {
    //The @PathVariable Long id annotation and parameter indicate that the method will take an account ID
    //from the URL and use it in the method.
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id " + id));
    //findByID: used with Spring Data JPA repositories to retrieve an entity by its primary key (ID). 
    //In the case of accountRepository.findById(id), the method attempts to find an Account entity with the given id. If an account with the specified ID exists, it's wrapped in an Optional. If not, an empty Optional is returned.        
    //The orElseThrow() method is part of the Optional class in Java. It's used to retrieve the value contained within an Optional object. If the Optional is empty (i.e., the value is not present), orElseThrow() throws an exception.
    }

    @GetMapping("/accounts")
    List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
