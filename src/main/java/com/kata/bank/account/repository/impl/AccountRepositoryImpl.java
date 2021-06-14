package com.kata.bank.account.repository.impl;


import com.kata.bank.account.exception.FunctionalException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.repository.IAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountRepositoryImpl implements IAccountRepository {

    private Map<Integer, Account> accountMap = new HashMap<>();

    public Account getAccount(Integer idAccount) throws FunctionalException {
        if(accountMap.containsKey(idAccount))
            return accountMap.get(idAccount);

        throw new FunctionalException("Account does not exit") ;
    }

    public Account save(Account account) {
        accountMap.put(account.getIdAccount(), account);
        return account;
    }

    @Override
    public boolean remove(Account account) {
        return accountMap.remove(account.getIdAccount(), account);
    }


}
