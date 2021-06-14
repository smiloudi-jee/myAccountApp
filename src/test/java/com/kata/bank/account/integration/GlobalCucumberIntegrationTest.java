package com.kata.bank.account.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber/result/integrationTest"},
        glue = {"com.kata.bank.account.integration.steps",
                "com.kata.bank.account.integration.commons"},
        strict = true)
public class GlobalCucumberIntegrationTest {

}
