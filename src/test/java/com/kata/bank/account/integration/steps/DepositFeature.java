package com.kata.bank.account.integration.steps;

import com.kata.bank.account.config.MyBankConfig;
import com.kata.bank.account.exception.FunctionalException;
import com.kata.bank.account.model.Account;
import com.kata.bank.account.services.IAccountService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = {MyBankConfig.class})
public class DepositFeature {

    @Autowired
    private IAccountService accountService;

    private double amount;
    private Integer idAccount;
    private Account account;

    @Given("I create an account as a new client")
    public void create_an_account_as_a_new_client() throws IOException, FunctionalException {
        Account account =
                Account.builder()
                        .idAccount(111111)
                        .balance(0)
                        .label("My Account")
                        .build();

        accountService.creatAccount(account);
    }

    @Given("As a bank client (\\w+)")
    public void as_a_bank_client(final String idAccount) throws Exception {
        // Test if idAccount is valid
        assertTrue(NumberUtils.isNumber(idAccount));
        this.idAccount = Integer.valueOf(idAccount);
    }

    @When("I want to make a deposit of {double} in my account")
    public void i_want_to_make_a_deposit_in_my_account(final double amount) throws Exception {
        // Test if amount is valid
        assertNotNull(amount);
        assertTrue(amount > 0);
        this.amount = amount;
        account = accountService.deposit(this.idAccount, this.amount);
    }

    @Then("the deposit is done")
    public void the_deposit_is_done() throws Exception {
        assertTrue(account.getBalance() == this.amount);
        assertTrue(account.getIdAccount().equals(this.idAccount));
    }

}
