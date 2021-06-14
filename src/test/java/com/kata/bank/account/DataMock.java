package com.kata.bank.account;

import com.kata.bank.account.model.Order;
import com.kata.bank.account.model.TypeOrder;

import java.time.LocalDateTime;

public class DataMock {

    public static Order getMockOrder(double balance) {
        return Order.builder().idOrder(1).idAccount(111111).amount(100)
                .balance(balance).date(LocalDateTime.now())
                .type(TypeOrder.DEPOSIT).build();
    }
}
