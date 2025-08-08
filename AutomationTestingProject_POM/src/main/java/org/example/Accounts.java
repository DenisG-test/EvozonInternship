package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class Accounts {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        page = browser.newPage();
    }

    public void registration() {
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
        emailField.fill("denis@yahoo.com");
        Locator passwordField = page.locator("#password");
        passwordField.fill("123456");
        Locator confirmPasswordField = page.locator("#confirmation");
        confirmPasswordField.fill("123456");
        Locator registerButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register"));
        registerButton.click();
    }

    public void login() {
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
    }

    public void close() {
        page.close();
        browser.close();
        playwright.close();
    }

    public static void main(String[] args) {
        Accounts accounts = new Accounts();
        accounts.setup();
        accounts.registration();
        accounts.login();
        accounts.close();
    }
}
