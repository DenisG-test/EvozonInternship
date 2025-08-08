package org.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomepageTest {

    @Test
    void homepageAssignment(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        Page page = browser.newPage();

        page.navigate("http://qa2magento.dev.evozon.com/");
        System.out.println("Page title: " + page.title());
        System.out.println("Page URL: " + page.url());

        Locator logoClick =  page.locator("#header a.logo img:first-child");
        logoClick.click();

        page.goBack();
        page.goForward();
        page.reload();

        assertEquals("http://qa2magento.dev.evozon.com/", page.url(), "URL after navigation actions is not as expected");

        page.close();

    }
}