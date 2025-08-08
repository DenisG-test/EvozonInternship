package org.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomepageTest extends BaseTest {

    @Test
    void homepageAssignment() {
        page.navigate("http://qa2magento.dev.evozon.com/");

        System.out.println("Page title: " + page.title());
        System.out.println("Page URL: " + page.url());

        Locator logoClick = page.locator("#header a.logo img:first-child");
        logoClick.click();

        page.goBack();
        page.goForward();
        page.reload();

        assertEquals("http://qa2magento.dev.evozon.com/", page.url(), "URL after navigation actions is not as expected");
    }
}
