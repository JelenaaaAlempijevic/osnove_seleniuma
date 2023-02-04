package p02_02_2023;
//1.Zadatak
//Kreirati klasu KatalonLoginTests za testove
//Base url: https://cms.demo.katalon.com
//Test #1: Visit login page from Nav bar
//Koraci:
//Ucitati home stranicu
//Kliknuti na My account link
//Verifikovati da je naslov stranice My account – Katalon Shop
//Verifikovati da se u url-u stranice javlja /my-account
//Za sve validacije ispisati odgovarajuce poruke u slucaju greske

//Test #2: Check input types
//Koraci:
//Ucitati /my-account stranicu
//Verifikovati da  polje za unos email-a za atribu type ima vrednost text
//Verifikovati da polje za unos lozinke za atribut type ima vrednost password
//Verifikovati da checkbox Remember me za atribut type ima vrednost checkbox
//Verifikovati da je Remember me checkbox decekiran. Koristan link kako naci informaciu da li je checkbox cekiran ili ne.
//Za sve validacije ispisati odgovarajuce poruke u slucaju greske

//Test #3: Display error when credentials are wrong
//Podaci:
//email: invalidemail@gmail.com
//password: invalid123
//Koraci:
//Ucitati /my-account stranicu
//Unesite email
//Unesite password
//Kliknite na login dugme
//Verifikovati da postoji element koji prikazuje gresku
//Verifikovati da je prikazana greska ERROR: Invalid email address
//Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//Verifikovati da smo idalje na login stranici posle greske, tako sto proveravamo da se url-u javlja /my-account


import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class KatalonLoginTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl= "https://cms.demo.katalon.com";
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver",
                "drivers/chromedriver.exe");

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseUrl);
    }

    @Test(priority = 1)
    @Description("TC-1 Visit login page from Nav bar")
    public void visitLogInPageFromNavBar()  {
        driver.findElement(By.linkText("MY ACCOUNT")).click();
        Assert.assertEquals(driver.getTitle(), "My account – Katalon Shop",
                "Title of page does not exist.");
        Assert.assertTrue(driver.getCurrentUrl().contains("/my-account"),
                "URL does not exist.");
    }


    @Test (priority = 2)
    @Description("TC-2 Check input types")
    public void checkInputTypes()  {
        driver.get("https://cms.demo.katalon.com/my-account");
        Assert.assertEquals(driver.findElement(By.id("username")).getAttribute("type"), "text",
                "Email input field incorrect.");
        Assert.assertEquals(driver.findElement(By.id("password")).getAttribute("type"), "password",
                "Password input field incorrect.");
        Assert.assertEquals(driver.findElement(By.id("rememberme")).getAttribute("type"), "checkbox",
                "Checkbox does not exist.");
        Assert.assertFalse(driver.findElement(By.id("rememberme")).isSelected(),"Checkbox should not be checked");
    }

    @Test (priority = 3)
    @Description("TC-3 Display error when credentials are wrong")
    public void displayErrorWhenCredentialsAreWrong()  {
        driver.get("https://cms.demo.katalon.com/my-account");
        driver.findElement(By.id("username")).sendKeys("invalidemail@gmail.com");
        driver.findElement(By.id("password")).sendKeys("invalid123");
        driver.findElement(By.name("login")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("woocommerce-error")));

        Assert.assertEquals(driver.findElement(By.className("woocommerce-error")).getText(),
                "ERROR: Invalid email address. Lost your password?",
                "Error is not displayed when credentials are invalid.");
        Assert.assertTrue(driver.getCurrentUrl().contains("/my-account"),
                "Not on my account page.");
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




