package com.kata.bank.account.model;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

@Builder
@Data
public class OpeartionLine {
    private double balance;
    private Operation operation;

    public OpeartionLine(double balance, Operation operation) {
        this.balance = balance;
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpeartionLine)) return false;
        OpeartionLine that = (OpeartionLine) o;
        return Double.compare(that.getBalance(), getBalance()) == 0 &&
                Objects.equals(getOperation(), that.getOperation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBalance(), getOperation());
    }

    @Override
    public String toString() {
        return "{balance=" + balance +", operation=" + operation +"}";
    }
}
