package com.kata.bank.account.repository;

import com.kata.bank.account.model.Order;

import java.util.List;

public interface IOrderRepository {

    Order addOrder(Order order);

    List<Order> loadAllByAccount(Integer account);
}
