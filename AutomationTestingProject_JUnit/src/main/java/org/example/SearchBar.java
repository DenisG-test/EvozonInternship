package org.example;

import com.microsoft.playwright.*;

public class SearchBar {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        Page page = browser.newPage();

        searchAssignment(page);

        browser.close();
        playwright.close();

    }
    public static void searchAssignment(Page page) {
        page.navigate("http://qa2magento.dev.evozon.com/");
        Locator searchBar = page.locator("#search");
        Locator searchButton = page.locator("#search_mini_form  button");//schimbare assignment locator
        searchBar.clear();
        searchBar.fill("woman");
        searchButton.click();
        page.close();
    }
}
