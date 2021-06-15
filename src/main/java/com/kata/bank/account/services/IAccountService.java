package com.kata.bank.account.services;

import com.kata.bank.account.exception.FunctionalException;
import com.kata.bank.account.exception.TechnicalException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Order;

import java.util.List;

public interface IAccountService {

    Account deposit(Integer idAccount, double amount) throws Exception;
    Account withdrawal(Integer idAccount, double amount) throws Exception;

    List<Order> checkHistory(Integer idAccount) throws TechnicalException;
    Integer creatAccount(Account openingAccount) throws TechnicalException;
    boolean closeAccount(Account oldAccount) throws TechnicalException;
}
