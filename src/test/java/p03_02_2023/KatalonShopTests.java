package p03_02_2023;
//Zadatak
//	Kreirati KatalonShopTests klasu:
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
//
//	Test #3:  Verify error is displayed when username is missing
//	Prioritet = 30
//	Koraci:
//Kliknite na my account link
//Klik na login dugme
//Verifikovati da je prikazana poruka Error: Username is required.

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class KatalonShopTests {
    private String baseUrl = "https://cms.demo.katalon.com";
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.manage().window().maximize();
    }
    @BeforeMethod
    public void beforeMethod(){
        driver.get(baseUrl + "/product/flying-ninja");
    }

    @Test(priority = 10)
    public void addingProductsWithQuantityToTheCart(){

        driver.findElement(By.xpath("//*[@class='quantity']/input")).clear();
        driver.findElement(By.xpath("//*[@class='quantity']/input")).sendKeys("3");
        driver.findElement(By.name("add-to-cart")).click();
        WebElement message = driver.findElement(By.className("woocommerce-message"));
        Assert.assertTrue(message.getText().contains("Flying Ninja"),
                "Message doesn't contain right message");
        driver.findElement(By.className("woocommerce-message"))
                .findElement(By.tagName("a")).click();
        Assert.assertEquals(driver.getCurrentUrl(),baseUrl+ "/cart/", "Url is not right");
        int cartElements = driver.findElements(By.xpath("//*[@class='entry-content']/div/form")).size();
        Assert.assertEquals(cartElements,1,"There are no products in the cart");

    }
    @Test(priority = 20)
    public void RemovingProductFromCart() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id='primary-menu']//li/a")).click();
        Assert.assertEquals(driver.getCurrentUrl(),baseUrl+ "/cart/", "Url is not right");
        int cartElements = driver.findElements(By.xpath("//*[@class='entry-content']/div/form")).size();
        Assert.assertEquals(cartElements,1,"There are no products in the cart");
        driver.findElement(By.xpath("//*[@class='product-remove']/a")).click();
        Thread.sleep(3000);
        cartElements = driver.findElements(By.xpath("//*[@class='entry-content']/div/form")).size();
        Assert.assertEquals(cartElements,0,"There are no products in the cart");

    }
    @Test(priority = 30)
    public void verifyErrorIsDisplayedWhenUsernameIsMissing(){
        //Click on my account link
        driver.findElement(By.xpath("//*[@id='primary-menu']//li[3]/a")).click();
        driver.findElement(By.name("login")).click();
        String errorMessage = driver.findElement(By
                        .xpath("//*[@class='woocommerce-error']/li"))
                .getText();
        Assert.assertEquals(errorMessage, "Error: Username is required.",
                "Error message is wrong" );

    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("AFTER TEST");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
