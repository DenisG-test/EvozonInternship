package org.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountsTest extends BaseTest {


    @Test
    void testRegistration() {
        page.navigate("http://qa2magento.dev.evozon.com/");

        Locator accountClick = page.locator("div.account-cart-wrapper a.skip-account span.label");
        accountClick.click();

        Locator registerClick = page.locator("#header-account div :nth-child(5) a");
        registerClick.click();

        page.getByLabel("First Name").fill("Denis");
        page.getByLabel("Middle Name/Initial").fill("Denis");
        page.getByLabel("Last Name").fill("Denis");
        page.getByLabel("Email Address").fill("denis24@yahoo.com");
        page.locator("#password").fill("123456");
        page.locator("#confirmation").fill("123456");

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Register")).click();

        assertTrue(
                page.url().contains("account") ||
                        page.isVisible("text=Thank you for registering"),
                "Registration was not successful"
        );
    }

    @Test
    void testLogin() {
        page.navigate("http://qa2magento.dev.evozon.com/");

        page.locator("div.account-cart-wrapper a.skip-account span.label").click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log In")).click();

        page.locator("#email").fill("denis@yahoo.com");
        page.locator("#pass").fill("123456");

        page.locator("#send2").click();

        assertTrue(page.locator("text=Welcome, Denis Denis Denis!").isVisible(), "Welcome message is not visible");
        assertTrue(page.locator("text=My Dashboard").isVisible(), "Dashboard is not visible");
        // Optional extra checks:
        // assertTrue(page.locator("text=Account Information").isVisible(), "Account Information section is not visible");
        // assertTrue(page.locator("text=Denis Denis Denis").isVisible(), "User's full name is not visible");
        // assertTrue(page.locator("text=denis1@yahoo.com").isVisible(), "User's email is not visible");
    }
}
