package d03_02_2023;
//Zadatak
//Kreirati KatalonShopTests klasu:
//baseUrl: https://cms.demo.katalon.com
//Test #1:  Adding product with quantity to the cart
//Prioritet = 10
//Koraci:
//Ucitati stranicu /product/flying-ninja/
//Unesite kolicinu 3
//Klik na Add to cart dugme
//Verifikovati da poruka sadrzi tekst “Flying Ninja”.
//Klik na Cart link iz navigacije
//Verifikovati da u url-u stoji /cart ruta
//Verifikovati da je broj proizvoda u korpi jednako 1
//
//Test #2:  Removing product from cart
//Prioritet = 20
//Koraci:
//Klik na Cart link iz navigacije
//Verifikovati da u url-u stoji /cart ruta
//Verifikovati da je broj proizvoda u korpi jednako 1
//Klik na remove dugme iz prvog reda
//Verifikovati da je broj proizvoda u korpi jedako 0
//
//Test #3:  Verify error is displayed when username is missing
//Prioritet = 30
//Koraci:
//Kliknite na my account link
//Klik na login dugme
//Verifikovati da je prikazana poruka Error: Username is required.
//
//Test #4:  Verify error is displayed when password is missing
//	Prioritet = 40
//	Koraci:
//Kliknite na my account link
//Unesite username customer
//Klik na login dugme
//Verifikovati da je prikazana poruka ERROR: The password field is empty.
//
//Test #5:  Verify error is displayed when password is wrong
//	Prioritet = 50
//	Koraci:
//Kliknite na my account link
//Unesite username customer
//Unesite nevalidan pass invalidpassword
//Klik na login dugme
//Verifikovati da je prikazana poruka ERROR: The password you entered for the username customer is incorrect. Lost your password?
//
//
//
//Test #6:  Verify error is displayed when user does not exist
//	Prioritet = 60
//	Koraci:
//Kliknite na my account link
//Unesite username invaliduser
//Unesite nevalidan pass (ex: pass1234)
//Klik na login dugme
//Verifikovati da je prikazana poruka ERROR: Invalid username. Lost your password?
//
//Test #7:  Verify successful login
//	Prioritet = 70
//	Koraci:
//Kliknite na my account link
//Unesite username customer
//Unesite validan pass crz7mrb.KNG3yxv1fbn
//Klik na login dugme
//Verifikovati na stranici pise Hello Katalon Parlitul_Changed
//	Dopunite pageve za sve sto je potrebno za ove testove, ako je potrebno kreirajte i nove pageve
import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class KatalonShopTests {
    private WebDriver driver;
    private String baseURL = "https://cms.demo.katalon.com";
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
        driver.get(baseURL);
    }

    @Test(priority = 10)
    @Description("TC1 - Verify that adding to cart works")
    public void addToCart(){
        driver.get(baseURL + "/product/flying-ninja/");
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("3");
        driver.findElement(By.name("add-to-cart")).click();
        Assert.assertTrue(driver.findElement(By.className("woocommerce-message")).getText().contains("Flying Ninja"),
                "The message does not contain 'Flying Ninja");
        driver.findElement(By.xpath("//li/a[contains(@href, 'cart')]")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/cart"), "URL does not contain '/cart'");
        List<WebElement> products = driver.findElements(By.className("cart_item"));
        Assert.assertTrue(products.size()==1, "Cart does not contain only one product");
    }

    @Test(priority = 20)
    @Description("TC2 - Verify that removing from cart works")
    public void removeFromCart() throws InterruptedException {
        driver.findElement(By.xpath("//li/a[contains(@href, 'cart')]")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/cart"), "URL does not contain '/cart'");
        List<WebElement> products = driver.findElements(By.className("cart_item"));
        Assert.assertTrue(products.size()==1, "Cart does not contain only one product");
        driver.findElement(By.xpath("//td[(@class='product-remove')]/a")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("cart_item"), 0));
        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(),0,
                "Cart is not empty");
    }

    @Test (priority = 30)
    @Description("TC3 - Verify that error is displayed when username is missing")
    public void usernameMissingError(){
        driver.findElement(By.xpath("//li/a[contains(@href, 'my-account')]")).click();
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div/ul[@class='woocommerce-error']/li")).getText(),
                "Error: Username is required.", "The shown message is different");
    }

    @Test (priority = 40)
    @Description("TC4 - Verify that error is displayed when password is missing")
    public void passwordMissingError(){
        driver.findElement(By.xpath("//li/a[contains(@href, 'my-account')]")).click();
        driver.findElement(By.name("username")).sendKeys("username");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div/ul[@class='woocommerce-error']/li")).getText(),
                "ERROR: The password field is empty.", "The shown message is different");
    }

    @Test(priority = 50)
    @Description("TC5 - Verify that error is displayed when password is wrong")
    public void passwordWrongError(){
        driver.findElement(By.xpath("//li/a[contains(@href, 'my-account')]")).click();
        driver.findElement(By.name("username")).sendKeys("customer");
        driver.findElement(By.name("password")).sendKeys("invalidpassword");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div/ul[@class='woocommerce-error']/li")).getText(),
                "ERROR: The password you entered for the username customer is incorrect. Lost your password?", "The shown message is different");
    }

    @Test(priority = 60)
    @Description("TC6 - Verify error is displayed when user does not exist")
    public void userDoesNotExist(){
        driver.findElement(By.xpath("//li/a[contains(@href, 'my-account')]")).click();
        driver.findElement(By.name("username")).sendKeys("invaliduser");
        driver.findElement(By.name("password")).sendKeys("pass1234");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div/ul[@class='woocommerce-error']/li")).getText(),
                "ERROR: Invalid username. Lost your password?", "The shown message is different");
    }

    @Test(priority = 70)
    @Description("TC7 - Verify successful login")
    public void verifySuccessfulLogin(){
        driver.findElement(By.xpath("//li/a[contains(@href, 'my-account')]")).click();
        driver.findElement(By.name("username")).sendKeys("customer");
        driver.findElement(By.name("password")).sendKeys("crz7mrb.KNG3yxv1fbn");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='woocommerce-MyAccount-content']/div/p")).getText().contains("Hello Katalon Parlitul_Changed"),
                "The shown message for login not the same.");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("AFTER TEST");
    }

    @AfterClass
    public void afterClass() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}