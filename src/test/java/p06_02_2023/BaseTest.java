package p06_02_2023;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.katalon_pages.CartPage;
import pages.katalon_pages.NavPage;
import pages.katalon_pages.ProductPage;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl = "https://cms.demo.katalon.com";
    protected ProductPage productPage;
    protected NavPage navPage;
    protected CartPage cartPage;


    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        navPage = new NavPage(driver,wait);
        productPage = new ProductPage(driver, wait);
        cartPage = new CartPage(driver, wait);

    }


    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseUrl);
    }


    @AfterMethod
    public void afterMethod() {

    }

    @AfterClass
    public void afterClass() throws InterruptedException {

        Thread.sleep(2000);
        driver.quit();
    }
}