package com.kata.bank.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Order {
    private Integer idOrder;
    private LocalDateTime date;
    private TypeOrder type;
    private Integer idAccount;
    private double amount;
    private double balance;
}
