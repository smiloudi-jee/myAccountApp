package com.kata.bank.account.repository;

import com.kata.bank.account.exception.TechnicalException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Order;
import com.kata.bank.account.model.TypeOrder;
import com.kata.bank.account.repository.impl.AccountRepositoryImpl;
import com.kata.bank.account.repository.impl.OrderRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kata.bank.account.DataMock.getMockOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderRepositoryTest {

    @InjectMocks
    private OrderRepositoryImpl orderRepository;

    @Test
    public void when_addOrder_then_succes() throws Exception {
        // GIVEN
        Map<Integer, Order> accountMap = new HashMap<>();
        accountMap.put(1, getMockOrder(100));
        FieldSetter.setField(orderRepository, OrderRepositoryImpl.class.getDeclaredField("orderMap"), accountMap);
        Order order = Order.builder().idAccount(111111).amount(100)
                .balance(150).date(LocalDateTime.now())
                .type(TypeOrder.DEPOSIT).build();;
        // WHEN
        Order actual = orderRepository.addOrder(order);
        // THEN
        assertEquals(150, actual.getBalance());
    }

    @Test
    public void when_addOrder_then_failed() throws Exception {
        // GIVEN
        Map<Integer, Order> accountMap = null;
        FieldSetter.setField(orderRepository, OrderRepositoryImpl.class.getDeclaredField("orderMap"), accountMap);
        Order order = Order.builder().idAccount(111111).amount(100)
                .balance(150).date(LocalDateTime.now())
                .type(TypeOrder.DEPOSIT).build();;
        // WHEN

        // WHEN
        Exception exception = assertThrows(TechnicalException.class, () -> {
            orderRepository.addOrder(order);
        });

        //Then
        assertEquals("Method addOrder failed", exception.getMessage());
    }

    @Test
    public void when_loadAllByAccount_then_succes() throws Exception {
        // GIVEN
        Map<Integer, Order> accountMap = new HashMap<>();
        accountMap.put(1, getMockOrder(100));
        accountMap.put(2, getMockOrder(200));
        FieldSetter.setField(orderRepository, OrderRepositoryImpl.class.getDeclaredField("orderMap"), accountMap);

        // WHEN
        List<Order> actual = orderRepository.loadAllByAccount(111111);
        // THEN
        assertEquals(2, actual.size());
    }

    @Test
    public void when_loadAllByAccount_then_failed() throws Exception {
        // GIVEN
        Map<Integer, Order> accountMap = null;
        FieldSetter.setField(orderRepository, OrderRepositoryImpl.class.getDeclaredField("orderMap"), accountMap);

        // WHEN
        Exception exception = assertThrows(TechnicalException.class, () -> {
            orderRepository.loadAllByAccount(111111);
        });

        //Then
        assertEquals("Method loadAllByAccount failed", exception.getMessage());
    }
}
