package com.kata.bank.account.services;

import com.kata.bank.account.exception.FunctionalException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Order;

import java.util.List;

public interface IAccountService {

    Account deposit(Integer idAccount, double amount) throws FunctionalException;
    Account withdrawal(Integer idAccount, double amount) throws FunctionalException;
    List<Order> checkHistory(Integer idAccount);

    Integer creatAccount(Account openingAccount) throws FunctionalException;
    boolean closeAccount(Account oldAccount) throws FunctionalException;
}
