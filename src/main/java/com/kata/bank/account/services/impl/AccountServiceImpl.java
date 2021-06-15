package com.kata.bank.account.services.impl;


import com.kata.bank.account.exception.TechnicalException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Order;
import com.kata.bank.account.model.TypeOrder;
import com.kata.bank.account.repository.IAccountRepository;
import com.kata.bank.account.repository.IOrderRepository;
import com.kata.bank.account.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Account deposit(Integer idAccount, double amount) throws Exception {
        Account account = accountRepository.getAccount(idAccount);
        account.setBalance(Double.sum(account.getBalance(), amount));
        accountRepository.save(account);

        orderRepository.addOrder(
                Order.builder().idAccount(idAccount).amount(amount)
                        .balance(account.getBalance()).date(LocalDateTime.now())
                        .type(TypeOrder.DEPOSIT).build());

        return account;
    }

    @Override
    public Account withdrawal(Integer idAccount, double amount) throws Exception {
        Account account = accountRepository.getAccount(idAccount);
        account.setBalance(Double.sum(account.getBalance(), -amount));
        accountRepository.save(account);

        orderRepository.addOrder(
                Order.builder().idAccount(idAccount).amount(amount)
                        .balance(account.getBalance()).date(LocalDateTime.now())
                        .type(TypeOrder.WITHDRAWL).build());

        return account;
    }

    @Override
    public List<Order> checkHistory(Integer idAccount) throws TechnicalException {
        return orderRepository.loadAllByAccount(idAccount);
    }

    @Override
    public Integer creatAccount(Account openingAccount) throws TechnicalException {
        return accountRepository.save(openingAccount).getIdAccount();
    }

    @Override
    public boolean closeAccount(Account oldAccount) throws TechnicalException {
        return accountRepository.remove(oldAccount);
    }

}
