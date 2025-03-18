package com.jb.banking_app.service;
import java.util.List;
import com.jb.banking_app.dto.AccountDto;
import com.jb.banking_app.dto.TransactionDto;
import com.jb.banking_app.dto.TransferFundDto;

public interface AccountService {

    List<AccountDto> getAllAccounts();

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    void deleteAccount(Long id);

    void transferFunds(TransferFundDto transferFundDto);

    List<TransactionDto> getAccountTransaction(Long accountId);

}//