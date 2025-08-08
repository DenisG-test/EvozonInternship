package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class AccountsTest {

    @Test
    void testRegistration() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
        );

        Page page = browser.newPage();
        page.navigate("http://qa2magento.dev.evozon.com/");

        Locator accountClick = page.locator("div.account-cart-wrapper a.skip-account span.label");
        accountClick.click();

        Locator registerClick = page.locator("#header-account div :nth-child(5) a");
        registerClick.click();

        Locator firstNameField = page.getByLabel("First Name");
        firstNameField.fill("Denis");

        Locator middleNameField = page.getByLabel("Middle Name/Initial");
        middleNameField.fill("Denis");

        Locator lastNameField = page.getByLabel("Last Name");
        lastNameField.fill("Denis");

        Locator emailField = page.getByLabel("Email Address");
        emailField.fill("denis1@yahoo.com");

        Locator passwordField = page.locator("#password");
        passwordField.fill("123456");

        Locator confirmPasswordField = page.locator("#confirmation");
        confirmPasswordField.fill("123456");

        Locator registerButton = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Register"));
        registerButton.click();

        assertTrue(page.url().contains("account") || page.isVisible(     "text=Thank you for registering"), "Registration was not successful");


        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    void testLogin() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
        );

        Page page = browser.newPage();
        page.navigate("http://qa2magento.dev.evozon.com/");

        Locator accountClick = page.locator("div.account-cart-wrapper a.skip-account span.label");
        accountClick.click();
        Locator loginClick = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log In"));
        loginClick.click();

        Locator emailInputField = page.locator("#email");
        emailInputField.fill("denis@yahoo.com");

        Locator passwordInputField = page.locator("#pass");
        passwordInputField.fill("123456");

        Locator loginButton = page.locator("#send2");
        loginButton.click();

        assertTrue(page.locator("text=Welcome, Denis Denis Denis!").isVisible(),     "Welcome message is not visible");
        assertTrue(page.locator("text=My Dashboard").isVisible(),        "Dashboard is not visible");
//        assertTrue(page.locator("text=Account Information").isVisible(), "Account Information section is not visible");
//        assertTrue(page.locator("text=Denis Denis Denis").isVisible(),   "User's full name is not visible");
//        assertTrue(page.locator("text=denis1@yahoo.com").isVisible(), "User's email is not visible");
    }

}