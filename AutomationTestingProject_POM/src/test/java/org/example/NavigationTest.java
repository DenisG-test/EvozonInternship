package org.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NavigationTest extends BaseTest {

    @Test
    void navigationGooglePage() {
        page.navigate("https://www.google.com");
        assertEquals("Google", page.title(), "The title is 'Google'");
        System.out.println("Page title: " + page.title());
    }

    @Test
    void testNavigationAssignment() {
        page.navigate("http://qa2magento.dev.evozon.com/");
        Locator navItem = page.locator("#nav li.level0:nth-child(5)");

        assertTrue(navItem.isVisible(), "The navigation item should be visible");
        navItem.hover();
        navItem.click();

        assertTrue(page.url().contains("sale"), "URL contains 'sale' after clicking");
    }

    @Test
    void testNewProductListAssignment() {
        page.navigate("http://qa2magento.dev.evozon.com/");
        Locator productNames = page.locator("li.item.last img[alt]");

        int count = productNames.count();
        assertEquals(5, count, "The number of products should be 5");

        System.out.println("Total products found: " + count);
        for (int i = 0; i < count; i++) {
            String productName = productNames.nth(i).getAttribute("alt");
            assertNotNull(productName, "Product " + (i + 1) + " should have a non-null alt attribute");
            System.out.println("Product " + (i + 1) + ": " + productName);
        }
    }
}
