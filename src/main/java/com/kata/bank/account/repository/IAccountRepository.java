package com.kata.bank.account.repository;

import com.kata.bank.account.exception.FunctionalException;
import com.kata.bank.account.exception.TechnicalException;
import com.kata.bank.account.model.Account;

public interface IAccountRepository {

    Account getAccount(Integer idAccount) throws FunctionalException;

    Account save(Account account) throws TechnicalException;

    boolean remove(Account account) throws TechnicalException;
}
