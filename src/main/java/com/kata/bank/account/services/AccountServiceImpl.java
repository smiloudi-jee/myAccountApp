package com.kata.bank.account.services;


import com.kata.bank.account.exception.FunctionalException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Order;
import com.kata.bank.account.model.TypeOrder;
import com.kata.bank.account.repository.IAccountRepository;
import com.kata.bank.account.repository.IOrderRepository;
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
    public Account deposit(Integer idAccount, double amount) throws FunctionalException {
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
    public Account withdrawal(Integer idAccount, double amount) throws FunctionalException {
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
    public List<Order> checkHistory(Integer idAccount) {
        return orderRepository.loadAllByAccount(idAccount);
    }

    @Override
    public Integer creatAccount(Account openingAccount) {
        return accountRepository.save(openingAccount).getIdAccount();
    }

    @Override
    public boolean closeAccount(Account oldAccount) {
        return accountRepository.remove(oldAccount);
    }

}
