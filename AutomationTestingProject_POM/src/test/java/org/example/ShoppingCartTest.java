package org.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest extends BaseTest {

    @Test
    void testShoppingCartAssertions() {

        page.navigate("http://qa2magento.dev.evozon.com/");

        Locator menuItem = page.locator("#nav li.level0:nth-child(2)");
        assertTrue(menuItem.isVisible(), "Main menu item should be visible");
        menuItem.hover();

        Locator subMenuItem = page.locator("#nav li.nav-2-2 a");
        assertTrue(subMenuItem.isVisible(), "Submenu item should be visible");
        subMenuItem.click();

        // First product
        Locator productImage = page.locator("#product-collection-image-403");
        assertTrue(productImage.isVisible(), "First product should be visible");
        String expectedProductName = productImage.getAttribute("alt");
        assertNotNull(expectedProductName, "The selected product should have a title");
        productImage.click();

        // Select color and size
        Locator swatchOption1 = page.locator("#swatch27");
        assertTrue(swatchOption1.isVisible(), "First swatch option should be visible");
        swatchOption1.click();

        Locator swatchOption2 = page.locator("#swatch77");
        assertTrue(swatchOption2.isVisible(), "Second swatch option should be visible");
        swatchOption2.click();

        Locator quantityField = page.locator("#qty");
        assertTrue(quantityField.isVisible(), "Quantity field should be visible");
        quantityField.fill("4");

        Locator addToCartButton = page.locator("#product_addtocart_form button");
        assertTrue(addToCartButton.isVisible(), "Add to Cart button should be visible");
        addToCartButton.click();


        page.navigate("http://qa2magento.dev.evozon.com/men/shirts.html");

        Locator anotherProductImage = page.locator("#product-collection-image-402");
        assertTrue(anotherProductImage.isVisible(), "Second product should be visible");
        String expectedSecondProductName = anotherProductImage.getAttribute("alt");
        assertNotNull(expectedSecondProductName, "Second selected product should have a title");
        anotherProductImage.click();

        //Select color and size
        Locator anotherSwatchOption1 = page.locator("#swatch22");
        assertTrue(anotherSwatchOption1.isVisible(), "First swatch option for second product should be visible");
        anotherSwatchOption1.click();

        Locator anotherSwatchOption2 = page.locator("#swatch77");
        assertTrue(anotherSwatchOption2.isVisible(), "Second swatch option for second product should be visible");
        anotherSwatchOption2.click();

        Locator anotherQuantityField = page.locator("#qty");
        assertTrue(anotherQuantityField.isVisible(), "Quantity field for second product should be visible");
        anotherQuantityField.fill("4");

        Locator anotherAddToCartButton = page.locator("#product_addtocart_form button");
        assertTrue(anotherAddToCartButton.isVisible(), "Add to Cart button for second product should be visible");
        anotherAddToCartButton.click();
    }
}
