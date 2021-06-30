package com.kata.bank.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Operation {

    private LocalDateTime date;
    private TypeOrder type;
    private double amount;

    public enum TypeOrder {
        DEPOSIT, WITHDRAWL
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operation)) return false;
        Operation order = (Operation) o;
        return Double.compare(order.getAmount(), getAmount()) == 0 &&
                Objects.equals(getDate(), order.getDate()) &&
                getType() == order.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getType(), getAmount());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "Operation{date=" + date.format(formatter) +", type=" + type +", amount=" + amount +"}";
    }
}
