package org.example;

import com.microsoft.playwright.*;

public class Navigation {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        page = browser.newPage();
    }

    public void navigateToGoogle() {
        page.navigate("https://google.com/");
        System.out.println("Page title: " + page.title());
    }

    public void navigationAssignment() {
        page.navigate("http://qa2magento.dev.evozon.com/");
        Locator myLocator = page.locator("#nav li.level0:nth-child(5)");
        myLocator.hover();
        myLocator.click();
    }

    public void newProductListAssignment() {
        page.navigate("http://qa2magento.dev.evozon.com/");
        Locator productNames = page.locator("li.item.last img[alt]");
        int count = productNames.count();
        System.out.println("Total products found: " + count);
        for (int i = 0; i < count; i++) {
            String productName = productNames.nth(i).getAttribute("alt");
            System.out.println("Product " + (i + 1) + ": " + productName);
        }
    }

    public void close() {
        page.close();
        browser.close();
        playwright.close();
    }

    public static void main(String[] args) {
        Navigation navigation = new Navigation();
        navigation.setup();
        navigation.navigateToGoogle();
        navigation.navigationAssignment();
        navigation.newProductListAssignment();
        navigation.close();
    }
}
