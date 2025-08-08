package org.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchBarTest {

    @Test
    void searchBar(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        Page page = browser.newPage();

        page.navigate("http://qa2magento.dev.evozon.com/");

        Locator searchBar = page.locator("#search");
        searchBar.clear();
        searchBar.fill("woman");

        Locator searchButton = page.locator("#search_mini_form  button");
        searchButton.click();

        assertTrue(page.url().contains("woman"), "URL contains 'woman' after clicking");

        Locator noResultsText = page.locator("p.note-msg");
        assertTrue(noResultsText.isVisible(), "The search did not return the expected 'no results' message");

        page.close();
    }
}