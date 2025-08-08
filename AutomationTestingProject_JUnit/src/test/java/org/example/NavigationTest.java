package org.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NavigationTest {

    @Test
    void navigationGooglePage(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        Page page = browser.newPage();

        page.navigate("https://www.google.com");
        assertEquals("Google", page.title(), "The tittle is 'Google'");

        System.out.println("Page title: " +page.title());
        page.close();
        browser.close();
    }

    @Test
    void testNavigationAssignment(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        Page page = browser.newPage();

        page.navigate("http://qa2magento.dev.evozon.com/");
        Locator myLocator = page.locator("#nav li.level0:nth-child(5)");

        assertTrue(myLocator.isVisible(), "The navigation item should be visible");

        myLocator.hover();
        myLocator.click();

        assertTrue(page.url().contains("sale"), "URL contains 'sale' after clicking");

        page.close();
        browser.close();
    }
    @Test
    void testNewProductListAssignment() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        Page page = browser.newPage();

        page.navigate("http://qa2magento.dev.evozon.com/");
        Locator productNames = page.locator("li.item.last img[alt]");

        int count = productNames.count();
        assertEquals(5,count, "The number of products should be 5");

        System.out.println("Total products found: " + count);
        for (int i = 0; i < count; i++) {
            String productName = productNames.nth(i).getAttribute("alt");
            assertNotNull(productName, "Product " + (i + 1) + " should have a non-null alt attribute");
            System.out.println("Product " + (i + 1) + ": " + productName);
        }

        page.close();
        browser.close();
    }
}