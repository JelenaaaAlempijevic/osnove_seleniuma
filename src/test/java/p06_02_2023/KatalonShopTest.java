package p06_02_2023;
//Kreirati N klasu:
//	baseUrl: https://cms.demo.katalon.com
//	Test #1:  Adding product with quantity to the cart
//	Prioritet = 10
//	Koraci:
//Ucitati stranicu /product/flying-ninja/
//Unesite kolicinu 3
//Klik na Add to cart dugme
//Verifikovati da poruka sadrzi tekst “Flying Ninja”.
//Klik na Cart link iz navigacije
//Verifikovati da u url-u stoji /cart ruta
//Verifikovati da je broj proizvoda u korpi jednako 1
//
//	Test #2:  Removing product from cart
//	Prioritet = 20
//	Koraci:
//Klik na Cart link iz navigacije
//Verifikovati da u url-u stoji /cart ruta
//Verifikovati da je broj proizvoda u korpi jednako 1
//Klik na remove dugme iz prvog reda
//Verifikovati da je broj proizvoda u korpi jedako 0


import com.sun.org.glassfish.gmbal.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import p31_01_2023.Helper;

import java.io.IOException;

public class KatalonShopTest extends BaseTest {

    //    @Test
    public void verifyNavLinks() throws IOException {

        String cartLink = navPage.getCartLink().getAttribute("href");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(new Helper().getHTTPResponseStatusCode(cartLink),
                200,
                "Cart link invalid status code.");

        String checkoutLink = navPage.getCheckoutLink().getAttribute("href");

        softAssert.assertEquals(new Helper().getHTTPResponseStatusCode(checkoutLink),
                200,
                "Checkout link invalid status code.");

        softAssert.assertAll();
    }


    @Test(priority = 10)
    @Description("Test #1:  Adding product with quantity to the cart")
    public void addingProductWithQuantityToTheCart() {
        driver.get(baseUrl + "/product/flying-ninja/");
        productPage.getQuanitityInput().clear();
        productPage.getQuanitityInput().sendKeys("3");
        productPage.getAddToCartButton().click();
        Assert.assertTrue(productPage.getMessageBox().getText().contains("Flying Ninja"),
                "Wrong message.");
        navPage.getCartLink().click();

        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl + "/cart/",
                "Not on cart page.");
        cartPage.waitForNumberOfRowsToBe(1);

    }


    @Test
    @Description("Test #2:  Removing product from cart")
    public void removingProductFromCart(){
            navPage.getCartLink().click();
            Assert.assertTrue(driver.getCurrentUrl().contains("/cart/"), "The URL does not contain the '/cart/' part");
            Assert.assertEquals(cartPage.getTableRows().size(), 1, "The cart does not contain 1 product");
            cartPage.getRemoveItemButtonByRow(1).click();
            cartPage.waitForNumberOfRowsToBe(0);

    }
}