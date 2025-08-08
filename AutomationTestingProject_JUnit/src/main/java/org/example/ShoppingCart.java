package org.example;

import com.microsoft.playwright.*;

public class ShoppingCart {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500));
        Page page = browser.newPage();

        addProductToCartAssignment(page);
        removeProductToCartAssignment(page);


        browser.close();
        playwright.close();
    }

    public static void addProductToCartAssignment(Page page) {
        page.navigate("http://qa2magento.dev.evozon.com/");

        Locator menuCategory = page.locator("#nav li.level0:nth-child(2)");
        menuCategory.hover();

        Locator subCategoryItem = page.locator("#nav li.nav-2-2 a");
        subCategoryItem.click();

        Locator productImage = page.locator("#product-collection-image-403");
        productImage.click();

        Locator colorOption = page.locator("#swatch27");
        colorOption.click();

        Locator sizeOption = page.locator("#swatch77");
        sizeOption.click();

        Locator quantityField = page.locator("#qty");
        quantityField.fill("4");

        Locator addToCartButton = page.locator("#product_addtocart_form button");
        addToCartButton.click();

    }

    public static void removeProductToCartAssignment(Page page) {
        page.navigate("http://qa2magento.dev.evozon.com/");

        Locator menuItem = page.locator("#nav li.level0:nth-child(2)");
        menuItem.hover();

        Locator subMenuItem = page.locator("#nav li.nav-2-2 a");
        subMenuItem.click();

        Locator productImage = page.locator("#product-collection-image-403");
        productImage.click();

        Locator swatchOption1 = page.locator("#swatch27");
        swatchOption1.click();

        Locator swatchOption2 = page.locator("#swatch77");
        swatchOption2.click();

        Locator quantityField = page.locator("#qty");
        quantityField.fill("4");

        Locator addToCartButton = page.locator("#product_addtocart_form button");
        addToCartButton.click();

        page.navigate("http://qa2magento.dev.evozon.com/men/shirts.html");

        Locator anotherProductImage = page.locator("#product-collection-image-402");
        anotherProductImage.click();

        Locator anotherSwatchOption1 = page.locator("#swatch22");
        anotherSwatchOption1.click();

        Locator anotherSwatchOption2 = page.locator("#swatch77");
        anotherSwatchOption2.click();

        Locator anotherQuantityField = page.locator("#qty");
        anotherQuantityField.fill("4");

        Locator anotherAddToCartButton = page.locator("#product_addtocart_form button");
        anotherAddToCartButton.click();

        Locator removeItemButton = page.locator("tr.first td.a-center a.btn-remove");
        removeItemButton.click();

        page.close();


    }
}
