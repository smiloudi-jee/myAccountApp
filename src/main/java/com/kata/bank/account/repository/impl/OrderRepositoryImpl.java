package com.kata.bank.account.repository.impl;


import com.kata.bank.account.exception.TechnicalException;
import com.kata.bank.account.model.Order;
import com.kata.bank.account.repository.IOrderRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryImpl implements IOrderRepository {

    private Map<Integer, Order> orderMap = new HashMap<>();

    @Override
    public Order addOrder(Order order) throws TechnicalException {
        if (orderMap != null) {
            orderMap.put(getSequenceIdOrder(), order);
            return order;
        }
        throw new TechnicalException("Method addOrder failed");
    }

    @Override
    public List<Order> loadAllByAccount(Integer account) throws TechnicalException {
        if (orderMap != null) {
            return orderMap.values().stream()
                    .filter(order -> order.getIdAccount().equals(account))
                    .collect(Collectors.toList());
        }
        throw new TechnicalException("Method loadAllByAccount failed");

    }

    private Integer getSequenceIdOrder() {
        Integer idOrder = orderMap.keySet().size() > 0 ? Collections.max(orderMap.keySet()) : 0;
        return Integer.sum(idOrder, 1);
    }
}
