package com.kata.bank.account.services;

import com.kata.bank.account.exception.FunctionalException;
import com.kata.bank.account.exception.TechnicalException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Order;
import com.kata.bank.account.repository.IAccountRepository;
import com.kata.bank.account.repository.IOrderRepository;
import com.kata.bank.account.repository.impl.AccountRepositoryImpl;
import com.kata.bank.account.repository.impl.OrderRepositoryImpl;
import com.kata.bank.account.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.kata.bank.account.DataMock.getMockListOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void when_deposit_then_succes() throws Exception {
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
    public void when_deposit_then_failed() throws Exception {
        // Given
        Integer idAccount = 111111;
        Account account = Account.builder().idAccount(111111).balance(10).build();
        when(accountRepositoryMock.getAccount(idAccount)).thenThrow(new FunctionalException("Account does not exit"));

        // When
        Exception exception = assertThrows(FunctionalException.class, () -> {
            accountService.deposit(idAccount, 100);
        });

        //Then
        verify(accountRepositoryMock, times(1)).getAccount(any());
        assertEquals("Account does not exit", exception.getMessage());
    }

    @Test
    public void when_withdrawal_then_succes() throws Exception {
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
    public void when_withdrawal_then_failed() throws Exception {
        // Given
        Integer idAccount = 111111;
        Account account = Account.builder().idAccount(111111).balance(10).build();
        when(accountRepositoryMock.getAccount(idAccount)).thenThrow(new FunctionalException("Account does not exit"));

        // When
        Exception exception = assertThrows(FunctionalException.class, () -> {
            accountService.withdrawal(idAccount, 100);
        });

        //Then
        verify(accountRepositoryMock, times(1)).getAccount(any());
        assertEquals("Account does not exit", exception.getMessage());
    }

    @Test
    public void when_closeAccount_then_succes() throws Exception {
        // Given
        Account account = Account.builder().idAccount(111111).balance(100).build();
        when(accountRepositoryMock.remove(any(Account.class))).thenReturn(true);
        // When
        boolean actual = accountService.closeAccount(account);

        // Then
        assertTrue(actual);
    }

    @Test
    public void when_closeAccount_then_failed() throws Exception {
        // Given
        Account account = Account.builder().idAccount(111111).balance(100).build();
        when(accountRepositoryMock.remove(any(Account.class))).thenThrow(new TechnicalException("oh my god, an Exception"));

        // When
        Exception exception = assertThrows(TechnicalException.class, () -> {
            accountService.closeAccount(account);
        });

        //Then
        verify(accountRepositoryMock, times(1)).remove(any(Account.class));
        assertEquals("oh my god, an Exception", exception.getMessage());
    }

    @Test
    public void when_creatAccount_then_succes() throws Exception {
        // Given
        Account account = Account.builder().idAccount(111111).balance(100).build();
        when(accountRepositoryMock.save(any(Account.class))).thenReturn(account);

        // When
        Integer actual = accountService.creatAccount(account);

        // Then
        assertEquals(account.getIdAccount(), actual);
    }

    @Test
    public void when_creatAccount_then_failed() throws Exception {
        // Given
        Account account = Account.builder().idAccount(111111).balance(100).build();
        when(accountRepositoryMock.save(any(Account.class))).thenThrow(new TechnicalException("oh my god, an other Exception"));

        // When
        Exception exception = assertThrows(TechnicalException.class, () -> {
            accountService.creatAccount(account);
        });

        //Then
        verify(accountRepositoryMock, times(1)).save(any());
        assertEquals("oh my god, an other Exception", exception.getMessage());
    }

    @Test
    public void when_checkHistory_then_succes() throws Exception {
        // Given
        Integer idAccount = 111111;
        Account account = Account.builder().idAccount(111111).balance(100).build();
        when(orderRepositoryMock.loadAllByAccount(idAccount)).thenReturn(getMockListOrder());

        // When
        List<Order> actual = accountService.checkHistory(idAccount);

        // Then
        assertNotNull(actual);
        assertEquals(2, actual.size());
    }

    @Test
    public void when_checkHistory_then_failed() throws Exception {
        // Given
        Integer idAccount = 111111;
        when(orderRepositoryMock.loadAllByAccount(idAccount)).thenThrow(new TechnicalException("oh my god, an other Exception"));

        // When
        Exception exception = assertThrows(TechnicalException.class, () -> {
            accountService.checkHistory(idAccount);
        });

        //Then
        verify(orderRepositoryMock, times(1)).loadAllByAccount(any());
        assertEquals("oh my god, an other Exception", exception.getMessage());
    }
}
