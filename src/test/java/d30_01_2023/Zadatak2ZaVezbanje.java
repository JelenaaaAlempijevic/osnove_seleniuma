package d30_01_2023;
//2. Zadatak (ZA VEZBANJE)
//Modifikovati zadatak 1 tako da se skrol vrsi u vise iteracija, npr u 5

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak2ZaVezbanje {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement selectEl = driver.findElement(By.tagName("select"));
        Select select = new Select(selectEl);
        select.selectByValue("2000");

        WebElement footer = driver.findElement(By.className("footer"));
        WebElement button = driver.findElement(By.tagName("button"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int i = 0; i < 5; i++) {
            new Actions(driver).scrollToElement(footer).perform();
            Thread.sleep(3000);

//       potrebno je da se skroluje gore-dole jer dugme ne moze drugacije da postane klikabilno:
            new Actions(driver).scrollByAmount(0, -400).perform();
            new Actions(driver).scrollByAmount(0, 400).perform();
            Thread.sleep(3000);
            wait.until(ExpectedConditions.elementToBeClickable(button));
            button.click();
        }

        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(button)));

        System.out.println("Stranica je ucitana");
        Thread.sleep(5000);
        driver.quit();
    }
}
