package com.kata.bank.account.services;

import com.kata.bank.account.exception.AccountException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.Operation;
import com.kata.bank.account.services.impl.AccountServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountServicesTest {

    private IAccountService accountService;
    @Spy
    private Account account;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        accountService = new AccountServiceImpl(account);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void when_deposit_then_succes() throws Exception {
        // Given
        Double amount = 200.00;
        LocalDateTime date = LocalDateTime.now();

        // When
        accountService.deposit(amount, date);

        // Then
        verify(account, times(1)).addOpeartionLine(Operation.builder().amount(amount).date(date).type(Operation.TypeOrder.DEPOSIT).build());
        assertEquals(200, account.getOperationList().getLast().getBalance());
    }

    @Test(expected = AccountException.class)
    public void when_deposit_then_failed() throws AccountException {
        // Given
        Double amount = 0.00;
        LocalDateTime date = LocalDateTime.now();
        accountService.deposit(amount, date);
    }

    @Test
    public void when_withdrawal_then_succes() throws Exception {
        // Given
        Double amount = 100.00;
        Double amountDeposit = 200.00;
        LocalDateTime date = LocalDateTime.now();
        accountService.deposit(amountDeposit, date);

        // When
        accountService.withdrawal(amount, date);

        // Then
        verify(account, times(1)).addOpeartionLine(Operation.builder().amount(amount).date(date).type(Operation.TypeOrder.WITHDRAWL).build());
        assertEquals(100, account.getOperationList().getLast().getBalance());
    }

    @Test(expected = AccountException.class)
    public void when_withdrawal_then_failed() throws AccountException {
        // Given
        Double amount = 0.00;
        LocalDateTime date = LocalDateTime.now();
        accountService.withdrawal(amount, date);
    }

    @Test
    public void when_printHistory_then_succes() throws Exception {
        // Given
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime date = LocalDateTime.now();
        accountService.deposit(200.00, date);
        accountService.withdrawal(150.00, date);

        // When
        accountService.printHistory(account);

        // Then
        String firstOperation = "{balance=200.0, operation=Operation{date="+date.format(formatter)+", type=DEPOSIT, amount=200.0}}";
        String secondOperation = "{balance=50.0, operation=Operation{date="+date.format(formatter)+", type=WITHDRAWL, amount=150.0}}";
        assertEquals(firstOperation.concat(secondOperation), outContent.toString());
    }

    @Test(expected = AccountException.class)
    public void when_printHistory_then_failed() throws AccountException {
        // Given
        account.setOperationList(null);
        // When
        accountService.printHistory(account);
    }

}
