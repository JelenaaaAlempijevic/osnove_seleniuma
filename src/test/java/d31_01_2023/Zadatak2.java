package d31_01_2023;
//2. Zadatak
//Napisati program koji:
//Ucitava stranu https://itbootcamp.rs/
//Misem prelazi preko Vesti iz navigacionog menija
//Ceka da se prikaze padajuci meni za Vesti
//Misem prelazi preko Kursevi iz navigacionog menija
//Ceka da se prikaze padajuci meni za Kursevi
//Misem prelazi preko Prijava i pravilnik iz navigacionog menija
//Ceka da se prikaze padajuci meni za Prijava i pravilnik
//Koristan link. Mouse over preko seleniuma https://www.selenium.dev/documentation/webdriver/actions_api/mouse/#move-to-element

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://itbootcamp.rs/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        hoverable(driver, By.xpath("//li/a[text()='Vesti ']"));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[2]/ul[@class=' dropdown-menu']"))));

        hoverable(driver, By.xpath("//li/a[text()='Kursevi ']"));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[3]/ul[@class=' dropdown-menu']"))));

        hoverable(driver, By.xpath("//li/a[text()='Prijava i pravilnik ']"));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[4]/ul[@class=' dropdown-menu']"))));



        Thread.sleep(5000);
        driver.quit();
    }

    public static void hoverable(WebDriver driver, By by){
        WebElement hoverable = driver.findElement(by);
        new Actions(driver).moveToElement(hoverable).perform();
    }
}

