package com.kata.bank.account.services.impl;

import com.kata.bank.account.exception.AccountException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Operation;
import com.kata.bank.account.services.IAccountService;

import java.time.LocalDateTime;

public class AccountServiceImpl implements IAccountService {

    private Account account;

    public AccountServiceImpl(Account account) {
        this.account = account;
    }

    @Override
    public void deposit(Double amount, LocalDateTime date) throws AccountException {
        if (amount == null || Double.compare(amount, 0.0) <= 0)
            throw new AccountException("Amount to deposit is invalid");

        Operation deposit = Operation.builder().amount(amount).date(date).type(Operation.TypeOrder.DEPOSIT).build();
        account.setBalance(account.getBalance() + amount);
        account.addOpeartionLine(deposit);
    }

    @Override
    public void withdrawal(Double amount, LocalDateTime date) throws AccountException {
        if (amount == null || Double.compare(amount, 0.0) <= 0)
            throw new AccountException("Amount to withdrawal is invalid");

        Operation withdrawal = Operation.builder().amount(amount).date(date).type(Operation.TypeOrder.WITHDRAWL).build();
        account.setBalance(account.getBalance() - amount);
        account.addOpeartionLine(withdrawal);
    }

    @Override
    public void printHistory(Account account) throws AccountException {
        if (account.getOperationList() == null)
            throw new AccountException("account is null");

        account.getOperationList().forEach(opeartionLine -> System.out.print(opeartionLine.toString()));
    }
}
