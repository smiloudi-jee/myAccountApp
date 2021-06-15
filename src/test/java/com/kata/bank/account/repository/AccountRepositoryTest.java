package com.kata.bank.account.repository;

import com.kata.bank.account.exception.FunctionalException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.repository.impl.AccountRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AccountRepositoryTest {

    @InjectMocks
    private AccountRepositoryImpl accountRepository;

    @Test
    public void when_getAccount_then_succes() throws Exception {
        // GIVEN
        Map<Integer, Account> accountMap = new HashMap<>();
        accountMap.put(111111, Account.builder().balance(100).idAccount(111111).build());
        FieldSetter.setField(accountRepository, AccountRepositoryImpl.class.getDeclaredField("accountMap"), accountMap);

        // WHEN
        Account actual = accountRepository.getAccount(111111);

        // THEN
        assertEquals(100, actual.getBalance());
    }

    @Test
    public void when_getAccount_then_failed() throws Exception {
        // GIVEN
        Map<Integer, Account> accountMap = new HashMap<>();
        accountMap.put(111111, Account.builder().balance(100).idAccount(111111).build());
        FieldSetter.setField(accountRepository, AccountRepositoryImpl.class.getDeclaredField("accountMap"), accountMap);

        // WHEN
        FunctionalException exception = assertThrows(FunctionalException.class, () -> {
            accountRepository.getAccount(111222);
        });

        // THEN
        assertEquals("Account does not exit", exception.getMessage());
    }
}
