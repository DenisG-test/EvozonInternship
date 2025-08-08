package org.example;

import com.microsoft.playwright.*;

public class Homepage {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        Page page = browser.newPage();

        // Navigate to Google
        page.navigate("https://google.com/");
        System.out.println("Page title: " + page.title());

        homepageAssignment(page);
        accountAssignment(page);

        browser.close();
        playwright.close();
    }
    public static void homepageAssignment(Page page) {
        page.navigate("http://qa2magento.dev.evozon.com/");
        System.out.println("Page title: " + page.title());
        System.out.println("Page URL: " + page.url());
        Locator logoClick =  page.locator("#header a.logo img:first-child");// schimbare assignment locator
        logoClick.click();
        page.goBack();
        page.goForward();
        page.reload();
        page.close();
    }

    public static void accountAssignment(Page page) {
        page.navigate("http://qa2magento.dev.evozon.com/");
        Locator accountClick = page.locator("div.account-cart-wrapper a.skip-account span.label");
        accountClick.click();
        page.close();
    }
}
