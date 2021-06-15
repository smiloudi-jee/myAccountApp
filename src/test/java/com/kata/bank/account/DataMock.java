package com.kata.bank.account;

import com.kata.bank.account.model.Order;
import com.kata.bank.account.model.TypeOrder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class DataMock {

    public static Order getMockOrder(double balance) {
        return Order.builder().idOrder(1).idAccount(111111).amount(100)
                .balance(balance).date(LocalDateTime.now())
                .type(TypeOrder.DEPOSIT).build();
    }

    public static List<Order> getMockListOrder() {
        return Arrays.asList(getMockOrder(100), getMockOrder(200));
    }
}
