package com.kata.bank.account.services;

import com.kata.bank.account.exception.AccountException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.OpeartionLine;

import java.time.LocalDateTime;

public interface IAccountService {

    void deposit(Double amount, LocalDateTime date) throws AccountException;

    void withdrawal(Double amount, LocalDateTime date) throws AccountException;

    void printHistory(Account account) throws AccountException;
}
