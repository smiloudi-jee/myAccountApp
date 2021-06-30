package com.kata.bank.account.integration.steps;

import com.kata.bank.account.exception.AccountException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.model.OpeartionLine;
import com.kata.bank.account.model.Operation;
import com.kata.bank.account.services.IAccountService;
import com.kata.bank.account.services.impl.AccountServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.Spy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AccountFeature {
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    Account account = new Account();
    IAccountService accountService = new AccountServiceImpl(account);

    Double depositAmount;
    Double withdrawalAmount;
    LocalDateTime depositDate;
    LocalDateTime withdrawalDate;

    @Given("As a bank client")
    public void as_a_bank_client() {
        assertNotNull(account);
        assertTrue(account.getBalance() == 0);
        depositDate = LocalDateTime.now();
        withdrawalDate = LocalDateTime.now();
    }

    @When("I want to make a deposit of {double} in my account")
    public void i_want_to_make_a_deposit_in_my_account(final double amount) throws Exception {
        depositAmount = amount;
        accountService.deposit(depositAmount, depositDate);
    }

    @Then("the deposit is done")
    public void the_deposit_is_done() {
        assertEquals(200, account.getOperationList().getLast().getBalance());
    }

    @When("I want to make a withdrawal of {double} from my account")
    public void i_want_to_make_a_withdrawal_from_my_account(final double amount) throws Exception {
        withdrawalAmount = amount;
        accountService.withdrawal(withdrawalAmount, withdrawalDate);
    }

    @Then("the withdrawal is done")
    public void the_withdrawal_is_done() {
        assertEquals(-200, account.getOperationList().getLast().getBalance());
    }

    @When("I want to see the history of my operations")
    public void iWantToSeeTheHistoryOperationDateAmountBalanceOfMyOperations() throws AccountException {
        System.setOut(new PrintStream(outContent));

        accountService.deposit(200.00, depositDate);
        accountService.withdrawal(150.00, withdrawalDate);

        accountService.printHistory(account);
    }

    @Then("I can see history")
    public void i_can_see_history() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String firstOperation = "{balance=200.0, operation=Operation{date="+depositDate.format(formatter)+", type=DEPOSIT, amount=200.0}}";
        String secondOperation = "{balance=50.0, operation=Operation{date="+withdrawalDate.format(formatter)+", type=WITHDRAWL, amount=150.0}}";
        assertEquals(firstOperation.concat(secondOperation), outContent.toString());

        final PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }
}
