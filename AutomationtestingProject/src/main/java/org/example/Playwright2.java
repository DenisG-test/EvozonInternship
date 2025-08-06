package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.FilePayload;
import com.microsoft.playwright.options.SelectOption;

import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

public class Playwright2 {

        public static void main(String[] args) {
                Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
                Page page = browser.newPage();

                // Navigate to Google
                page.navigate("https://google.com/");
                System.out.println("Page title: " + page.title());

               // Call each assignment method
                homepageAssignment(page);
                accountAssignment(page);
                searchAssignment(page);
                newProductListAssignment(page);
                navigationAssignment(page);
                addProductToCartAssignment(page);

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
        }

        public static void accountAssignment(Page page) {
                page.navigate("http://qa2magento.dev.evozon.com/");
                Locator accountClick = page.locator("div.account-cart-wrapper a.skip-account span.label");
                accountClick.click();
        }

        public static void searchAssignment(Page page) {
                page.navigate("http://qa2magento.dev.evozon.com/");
                Locator searchBar = page.locator("#search");
                Locator searchButton = page.locator("#search_mini_form  button");//schimbare assignment locator
                searchBar.clear();
                searchBar.fill("woman");
                searchButton.click();
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
        }

        public static void navigationAssignment(Page page) {
                page.navigate("http://qa2magento.dev.evozon.com/");
                Locator myLocator = page.locator("#nav li.level0:nth-child(5)"); //schimbat selector
                myLocator.hover();
                myLocator.click();
        }

        public static void addProductToCartAssignment(Page page) {
                page.navigate("http://qa2magento.dev.evozon.com/");
                Locator addProduct = page.locator("#nav li.level0:nth-child(2)");
                addProduct.hover();
                page.locator("#nav li[class='level1 nav-2-2']").click();
                page.locator("#product-collection-image-403").click();
                page.locator("#swatch27").click();
                page.locator("#swatch77").click();
                page.locator("#qty").fill("4");
                page.locator("#product_addtocart_form button").click(); //schimbat

        }

}
