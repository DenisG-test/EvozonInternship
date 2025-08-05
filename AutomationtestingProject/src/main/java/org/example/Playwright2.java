package org.example;

import com.microsoft.playwright.*;

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
                page.click("#header > div > a > img.large");
                page.goBack();
                page.goForward();
                page.reload();
        }

        public static void accountAssignment(Page page) {
                page.navigate("http://qa2magento.dev.evozon.com/");
                page.click("#header > div > div.skip-links > div > a > span.label");
        }

        public static void searchAssignment(Page page) {
                page.navigate("http://qa2magento.dev.evozon.com/");
                page.locator("#search").clear();
                page.locator("#search").fill("woman");
                page.click("#search_mini_form > div.input-box > button");
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
                Locator myLocator = page.locator("#nav > ol > li.level0.nav-5.parent");
                myLocator.hover();
                myLocator.click();
        }

        public static void addProductToCartAssignment(Page page) {
                page.navigate("http://qa2magento.dev.evozon.com/");
                Locator myLocator = page.locator("#nav > ol > li.level0.nav-2.parent");
                myLocator.hover();
                page.click("#nav > ol > li.level0.nav-2.parent > ul > li.level1.nav-2-2");
                page.click("#product-collection-image-403");
                page.click("#swatch27 > span.swatch-label > img");
                page.click("#swatch77 > span.swatch-label");
                page.fill("#qty", "4");
                page.click("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button > span > span");
        }
}
