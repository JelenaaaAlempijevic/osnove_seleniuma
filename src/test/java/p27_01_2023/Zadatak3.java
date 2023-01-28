package p27_01_2023;
//3.Zadatak
//Napisati program koji ucitava stranicu https://github.com/orgs/embedly/repositories?q=&type=all&language=&sort=
//Klik na Type drawdown
//Klik na Public iz drowdowna
//Proverava da li je Clear dugme u desnom uglu prikazano
//Kilk na Clear filter u desnom uglu

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Zadatak3 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://github.com/orgs/embedly/repositories");

        WebElement type = driver.findElement(By.id("type-options"));
        type.click();
        Thread.sleep(2000);
        type.findElements(By.className("SelectMenu-item")).get(1).click();

        if(driver.findElement(By.className("issues-reset-query")).isDisplayed()){
            System.out.println("Clear dugme je vidljivo.");
        } else {
            System.out.println("Clear dugme nije vidljivo.");
        }

        driver.findElement(By.className("issues-reset-query")).click();

        Thread.sleep(2000);
        driver.quit();

    }

}
