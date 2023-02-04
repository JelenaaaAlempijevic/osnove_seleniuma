package d02_02_2023;
//1.Zadatak
//Kreirati BootstrapTableTests klasu koja ima:
//Base url: https://s.bootsnipp.com
//Test #1: Edit Row
//Podaci:
//First Name: ime polaznika
//Last Name: prezime polaznika
//Middle Name: srednje ime polanzika
//Koraci:
//Ucitati stranu /iframe/K5yrx
//Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//Klik na Edit dugme prvog reda
//Sacekati da dijalog za Editovanje bude vidljiv
//Popuniti formu podacima.
//Bice potrebno da pre unosa tekst pobrisete tekst koji vec postoji, za to se koristi metoda clear. Koristan link
//Klik na Update dugme
//Sacekati da dijalog za Editovanje postane nevidljiv
//Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
//Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
//Verifikovati da se u Middle Name celiji prvog reda tabele javlja uneto srednje ime
//Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//Test #2: Delete Row
//Podaci:
//First Name: ime polaznika
//Last Name: prezime polaznika
//Middle Name: srednje ime polanzika
//Koraci:
//Ucitati stranu /iframe/K5yrx
//Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//Klik na Delete dugme prvog reda
//Sacekati da dijalog za brisanje bude vidljiv
//Klik na Delete dugme iz dijaloga
//Sacekati da dijalog za Editovanje postane nevidljiv
//Verifikovati da je broj redova u tabeli za jedan manji
//Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//Test #3: Take a Screenshot
//Koraci:
//Ucitati stranu  /iframe/K5yrx
//Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//Kreirati screenshot stranice.
//Fajl cuvajte na putanji gde su vam bile slike od proslog domaceg. Na putanji: screenshots/slike.png

import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import p31_01_2023.Helper;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BootstrapTableTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://s.bootsnipp.com";
    private String firstName = "Jelena";
    private String lastName = "Alempijevic";
    private String middleName = "Goran";
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
        driver.get(baseUrl + "/iframe/K5yrx");
        Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com","Page title is not the same.");
    }

    @Test(priority = 1)
    @Description("TC-1 Edit Row")
    public void editRow()  {
        driver.findElement(By.xpath("//tr[1]//button[@data-target='#edit']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("modal-body"))));
        driver.findElement(By.id("fn")).clear();
        driver.findElement(By.id("fn")).sendKeys(firstName);
        driver.findElement(By.id("ln")).clear();
        driver.findElement(By.id("ln")).sendKeys(lastName);
        driver.findElement(By.id("mn")).clear();
        driver.findElement(By.id("mn")).sendKeys(middleName);
        driver.findElement(By.id("up")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("modal-body"))));
        Assert.assertEquals(driver.findElement(By.id("f1")).getText(),firstName,"The entered first name does not match.");
        Assert.assertEquals(driver.findElement(By.id("l1")).getText(),lastName,"The entered last name does not match.");
        Assert.assertEquals(driver.findElement(By.id("m1")).getText(),middleName,"The entered middle name does not match.");
    }


    @Test (priority = 2)
    @Description("TC-2 Delete Row")
    public void deleteRow() {
        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
        int count = 0;
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                count++;
            }
        }
            driver.findElement(By.xpath("//tr[1]//button[@data-target='#delete']")).click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("del"))));
            driver.findElement(By.id("del")).click();
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("del"))));
            Assert.assertNotSame(count, count - 1, "The number of rows does not changed.");

        }

    @Test (priority = 3)
    @Description("TC-3 Take a Screenshot")
    public void takeAScreenshot() throws IOException {

        new Helper().takeScreenshot(driver,"screenshots/slika.png");
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










