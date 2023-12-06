package com.mm.moneymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mm.moneymanagement.model.Account;
import com.mm.moneymanagement.repository.AccountRepository;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/account")
    Account newAccount(@RequestBody Account newAccount) {
        return accountRepository.save(newAccount);
    }

}
