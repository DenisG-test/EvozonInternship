package org.example;

import com.microsoft.playwright.*;

public class Navigation {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        Page page = browser.newPage();

        // Navigate to Google
        page.navigate("https://google.com/");
        System.out.println("Page title: " + page.title());

        navigationAssignment(page);
        newProductListAssignment(page);

        browser.close();
        playwright.close();

    }
    public static void navigationAssignment(Page page) {
        page.navigate("http://qa2magento.dev.evozon.com/");
        Locator myLocator = page.locator("#nav li.level0:nth-child(5)");
        myLocator.hover();
        myLocator.click();
    }
    public static void newProductListAssignment(Page page) {
        page.navigate("http://qa2magento.dev.evozon.com/");
        Locator productNames = page.locator("li.item.last img[alt]");
        int count = productNames.count();
        System.out.println("Total products found: " + count);
        for (int i = 0; i < count; i++) {
            String productName = productNames.nth(i).getAttribute("alt");
            System.out.println("Product " + (i + 1) + ": " + productName);
        }
        page.close();
    }
}
