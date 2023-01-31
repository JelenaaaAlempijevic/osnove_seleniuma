package p30_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UvodUEksplicitno {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10));

        driver.get("file:///C:/Users/Dell/Downloads/Zadatak4%20(2).html");

        driver.findElement(By.id("showInBtn")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id-0")));


//        wait.until(ExpectedConditions.urlContains("/profile"))
//        wait.unti(ExpectedConditions.titleContains("Profile Page"))
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("showInBtn")));
//        driver.findElement(By.id("showInBtn")).click();

        wait.until(ExpectedConditions.attributeContains(By.tagName("//intput[pass]"), "type", "text"));
//        search
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("a"), 10));

        Thread.sleep(5000);
        driver.quit();
    }
}
