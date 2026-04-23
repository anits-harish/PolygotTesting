package com.example.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.example.config.ConfigManager;
import org.testng.Assert;

public class SampleSteps {

    @Given("I navigate to the base url")
    public void i_navigate_to_the_base_url() {
        String baseUrl = ConfigManager.getProperty("baseUrl");
        System.out.println("Navigating to: " + baseUrl);
    }

    @When("I perform a sample action")
    public void i_perform_a_sample_action() {
        System.out.println("Performing sample action...");
    }

    @Then("I verify the sample result")
    public void i_verify_the_sample_result() {
        System.out.println("Verifying result...");
        Assert.assertTrue(true, "Sample assertion");
    }
}
