package org.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchBarTest extends BaseTest {

    @Test
    void searchBar() {

        page.navigate("http://qa2magento.dev.evozon.com/");

        Locator searchBar = page.locator("#search");
        searchBar.clear();
        searchBar.fill("woman");

        Locator searchButton = page.locator("#search_mini_form button");
        searchButton.click();

        assertTrue(page.url().contains("woman"), "URL contains 'woman' after clicking");

        Locator noResultsText = page.locator("p.note-msg");
        assertTrue(noResultsText.isVisible(), "The search did not return the expected 'no results' message");
    }
}
