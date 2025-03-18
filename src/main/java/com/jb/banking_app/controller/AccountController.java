package com.jb.banking_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jb.banking_app.dto.AccountDto;
import com.jb.banking_app.dto.TransactionDto;
import com.jb.banking_app.dto.TransferFundDto;
import com.jb.banking_app.service.AccountService;

@Controller
@RequestMapping
public class AccountController {

    private AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

//////////////////////////////////// 
    // Get All Accounts Rest API
    @GetMapping("/api/accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts()
    {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

//////////////////////////////////// 
    // Add Account Rest API
    @PostMapping("/api/accounts")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
    }

//////////////////////////////////// 
    // get Account by ID Rest API
    @GetMapping("/api/accounts/{id}")
    public ResponseEntity<AccountDto> getAccountbyId(@PathVariable("id") Long id)
    {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

//////////////////////////////////// 
    // deposit amount by ID Rest API
    @PutMapping("/api/accounts/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable("id") Long id, @RequestBody Map<String, Double> request)
    {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

//////////////////////////////////// 
    // withdraw amount by ID Rest API
    @PutMapping("/api/accounts/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable("id") Long id, @RequestBody Map<String, Double> request)
    {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

//////////////////////////////////// 
    // withdraw amount by ID Rest API
    @DeleteMapping("/api/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id)
    {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully");
    }

//////////////////////////////////// 
    // fund amount transfer by ID Rest API
    @PostMapping("/api/accounts/transfer")
    public ResponseEntity<String> transferFund(@RequestBody TransferFundDto transferFundDto)
    {
        accountService.transferFunds(transferFundDto);
        return ResponseEntity.ok("Fund Amount Transfered Successfully");
    }
//////////////////////////////////////
    // build Transaction Rest Api
    @GetMapping("/api/accounts/{id}/transactions")
    public ResponseEntity<List<TransactionDto>> fetchAccountTransactions(@PathVariable("id") Long accountId)
    {
        List<TransactionDto> transactions = accountService.getAccountTransaction(accountId);
        return ResponseEntity.ok(transactions);
    }
}//