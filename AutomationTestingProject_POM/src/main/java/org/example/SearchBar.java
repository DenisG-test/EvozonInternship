package org.example;

import com.microsoft.playwright.*;

public class SearchBar {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        page = browser.newPage();
    }

    public void navigateToHomePage() {
        page.navigate("http://qa2magento.dev.evozon.com/");
    }

    public void searchAssignment() {
        Locator searchBar = page.locator("#search");
        Locator searchButton = page.locator("#search_mini_form button");

        searchBar.clear();
        searchBar.fill("woman");
        searchButton.click();
    }

    public void close() {
        page.close();
        browser.close();
        playwright.close();
    }

    public static void main(String[] args) {
        SearchBar searchBar = new SearchBar();
        searchBar.setup();
        searchBar.navigateToHomePage();
        searchBar.searchAssignment();
        searchBar.close();
    }
}
