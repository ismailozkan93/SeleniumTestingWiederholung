package gmibank.stepdefinitions;

import gmibank.pages.GmibankLoginPage;
import gmibank.pages.GooglePage;
import gmibank.utilities.ConfigurationReader;
import gmibank.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

public class GmibankloginDef {
 GmibankLoginPage gmibankLoginPage=new GmibankLoginPage();

    @Given("TC01 goes to {string}")
    public void tc01_goes_to(String bank_link) throws InterruptedException {
        Driver.getDriver().get(bank_link);
        Thread.sleep(2000);
        gmibankLoginPage.loginDropdown.click();
        Thread.sleep(3000);
        gmibankLoginPage.signInButton.click();
    }

    @Given("user writes valid {string} and valid {string}")
    public void user_writes_valid_and_valid(String username, String password) throws InterruptedException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gmibankLoginPage.username.sendKeys(username);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gmibankLoginPage.password.sendKeys(password);
        gmibankLoginPage.loginButton.click();

        Thread.sleep(3000);
        gmibankLoginPage.loginDropdown.click();
        Thread.sleep(2000);
        gmibankLoginPage.signout.click();
    }

    @And("submit SignIn button")
    public void submit_SignIn_button() throws InterruptedException {
        Thread.sleep(3000);

        Thread.sleep(3000);
    }
    @Then("verify the login with new page")
    public void verify_the_login_with_new_page() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(Driver.getDriver().getCurrentUrl());
        Assert.assertFalse(Driver.getDriver().getCurrentUrl().contains("login"));
    }

}
