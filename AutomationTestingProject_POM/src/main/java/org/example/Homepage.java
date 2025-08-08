package org.example;

import com.microsoft.playwright.*;

public class Homepage {
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

    public void homepageAssignment() {
        page.navigate("http://qa2magento.dev.evozon.com/");
        System.out.println("Page title: " + page.title());
        System.out.println("Page URL: " + page.url());

        Locator logoClick = page.locator("#header a.logo img:first-child");
        logoClick.click();

        page.goBack();
        page.goForward();
        page.reload();
    }

    public void accountAssignment() {
        page.navigate("http://qa2magento.dev.evozon.com/");
        Locator accountClick = page.locator("div.account-cart-wrapper a.skip-account span.label");
        accountClick.click();
    }

    public void close() {
        page.close();
        browser.close();
        playwright.close();
    }

    public static void main(String[] args) {
        Homepage homapage = new Homepage();
        homapage.setup();
        homapage.navigateToGoogle();
        homapage.homepageAssignment();
        homapage.accountAssignment();
        homapage.close();
    }
}
