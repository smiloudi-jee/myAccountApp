package com.kata.bank.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Account {
    private double balance;

    private LinkedList<OpeartionLine> operationList = new LinkedList<>();

    public void addOpeartionLine(Operation operation) {
        this.operationList.add(new OpeartionLine(this.balance, operation));
    }
}
