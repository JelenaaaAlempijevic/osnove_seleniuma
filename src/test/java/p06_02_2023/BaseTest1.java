package p06_02_2023;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.DeleteDialogPage;
import pages.EditDialogPage;
import pages.TablePage;

import java.time.Duration;

public abstract class BaseTest1 {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl = "https://s.bootsnipp.com";
    protected TablePage tablePage;
    protected EditDialogPage editDialogPage;
    protected DeleteDialogPage deleteDialogPage;


    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        tablePage = new TablePage(driver, wait);
        editDialogPage = new EditDialogPage(driver, wait);
        deleteDialogPage = new DeleteDialogPage(driver, wait);

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
