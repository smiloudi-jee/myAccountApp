package com.kata.bank.account.repository;

import com.kata.bank.account.exception.TechnicalException;
import com.kata.bank.account.model.Order;

import java.util.List;

public interface IOrderRepository {

    Order addOrder(Order order) throws TechnicalException;

    List<Order> loadAllByAccount(Integer account) throws TechnicalException;
}
