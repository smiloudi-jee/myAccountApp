package com.kata.bank.account.services;

import com.kata.bank.account.exception.FunctionalException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Order;
import com.kata.bank.account.repository.IAccountRepository;
import com.kata.bank.account.repository.IOrderRepository;
import com.kata.bank.account.repository.impl.AccountRepositoryImpl;
import com.kata.bank.account.repository.impl.OrderRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServicesTest {

    private final IAccountRepository accountRepositoryMock = mock(AccountRepositoryImpl.class);
    private final IOrderRepository orderRepositoryMock = mock(OrderRepositoryImpl.class);

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void when_deposit_then_succes() throws FunctionalException {
        // Given
        Integer idAccount = 111111;
        Account account = Account.builder().idAccount(111111).balance(100).build();
        when(accountRepositoryMock.getAccount(idAccount)).thenReturn(account);
        when(orderRepositoryMock.addOrder(any(Order.class))).thenReturn(Order.builder().build());

        // When
        Account actual = accountService.deposit(idAccount, 100);

        // Then
        assertEquals(200, actual.getBalance());
    }

    @Test
    public void when_deposit_then_failed() throws FunctionalException {
        // Given
        Integer idAccount = 111111;
        Account account = Account.builder().idAccount(111111).balance(10).build();
        when(accountRepositoryMock.getAccount(idAccount)).thenThrow(new FunctionalException("Account does not exit"));

        // When
        FunctionalException exception = assertThrows(FunctionalException.class, () -> {
            accountService.deposit(idAccount, 100);
        });

        //Then
        verify(accountRepositoryMock, times(1)).getAccount(any());
        assertEquals("Account does not exit", exception.getMessage());
    }

    @Test
    public void when_withdrawal_then_succes() throws FunctionalException {
        // Given
        Integer idAccount = 111111;
        Account account = Account.builder().idAccount(111111).balance(100).build();
        when(accountRepositoryMock.getAccount(idAccount)).thenReturn(account);
        when(orderRepositoryMock.addOrder(any(Order.class))).thenReturn(Order.builder().build());

        // When
        Account actual = accountService.withdrawal(idAccount, 100);

        // Then
        assertEquals(0, actual.getBalance());
    }

    @Test
    public void when_withdrawal_then_failed() throws FunctionalException {
        // Given
        Integer idAccount = 111111;
        Account account = Account.builder().idAccount(111111).balance(10).build();
        when(accountRepositoryMock.getAccount(idAccount)).thenThrow(new FunctionalException("Account does not exit"));

        // When
        FunctionalException exception = assertThrows(FunctionalException.class, () -> {
            accountService.withdrawal(idAccount, 100);
        });

        //Then
        verify(accountRepositoryMock, times(1)).getAccount(any());
        assertEquals("Account does not exit", exception.getMessage());
    }
}
