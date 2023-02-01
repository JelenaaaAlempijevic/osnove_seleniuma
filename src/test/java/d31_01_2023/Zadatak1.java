package d31_01_2023;
////  1.Zadatak
////Napisati program koji:
////Podesava:
////implicitno cekanje za trazenje elemenata od 10s
////implicitno cekanje za ucitavanje stranice od 10s
////eksplicitno cekanje podeseno na 10s
////Podaci:
////Potrebno je u projektu ukljuciti 4 slike:
////front.jpg
////left.jpg
////right.jpg
////back.jpg
////        Koraci:
////Ucitava stranicu https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you
////Maksimizuje prozor
////Klik na edit ikonicu
////Klik na delete iz iskacuceg dijaloga
////Klik na Add Image dugme
////Sacekajte da se pojavi desni meni
////Uploadujte front.jpg sliku
////Sacekajte da je ispod uploada slike, broj slika 1.
////Klik na sliku
////Klik na Done dugme
////Sacekajte 2s
////Klik na Add Image dugme
////Sacekajte da se pojavi desni meni
////Uploadujte right.jpg sliku
////Sacekajte da je ispod uploada slike, broj slika 2.
////Klik na sliku
////Klik na Done dugme
////Sacekajte 2s
////Klik na Add Image dugme
////Sacekajte da se pojavi desni meni
////Uploadujte back.jpg sliku
////Sacekajte da je ispod uploada slike, broj slika 3.
////Klik na sliku
////Klik na Done dugme
////Sacekajte 2s
////Klik na Add Image dugme
////Sacekajte da se pojavi desni meni
////Uploadujte back.jpg sliku
////Sacekajte da je ispod uploada slike, broj slika 3.
////Klik na sliku
////Klik na Done dugme
////Sacekajte 2s
////Sacekajte da Next dugme bude klikljivo
////Klik na Next dugme
////Unesite tekst
////Klik na Next
////Klik na Preview
////Klik na Add to cart
////Sacekajte 5s
////Quit

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Zadatak1 {
    public static void main(String[] args) throws InterruptedException {


        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        driver.findElement(By.className("edit-image")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("image-option-remove"))));
        driver.findElement(By.id("image-option-remove")).click();

        driver.findElement(By.id("active-face")).click();
        wait.until(ExpectedConditions.textToBe(By.className("sc-brePNt"), "+ Add photo"));
        driver.findElement(By.id("imageUpload")).sendKeys(new File("test_data/front.jpg").getAbsolutePath());
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[contains(@class, 'sc-dIfARi')]//img"), 1));
        driver.findElement(By.xpath("//*[contains(@class, 'sc-dIfARi')]//img[1]")).click();
        WebElement done = driver.findElement(By.xpath("//button[text()='Done']"));
        done.click();
        Thread.sleep(2000);

        driver.findElement(By.id("active-face")).click();
        wait.until(ExpectedConditions.textToBe(By.className("sc-brePNt"), "+ Add photo"));
        driver.findElement(By.id("imageUpload")).sendKeys(new File("test_data/left.jpg").getAbsolutePath());
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[contains(@class, 'sc-dIfARi')]//img"), 2));
        driver.findElement(By.xpath("//*[contains(@class, 'sc-dIfARi')]//img[1]")).click();
        driver.findElement(By.xpath("//button[text()='Done']")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("active-face")).click();
        wait.until(ExpectedConditions.textToBe(By.className("sc-brePNt"), "+ Add photo"));
        driver.findElement(By.id("imageUpload")).sendKeys(new File("test_data/back.jpg").getAbsolutePath());
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[contains(@class, 'sc-dIfARi')]//img"), 3));
        driver.findElement(By.xpath("//*[contains(@class, 'sc-dIfARi')]//img[1]")).click();
        driver.findElement(By.xpath("//button[text()='Done']")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("active-face")).click();
        wait.until(ExpectedConditions.textToBe(By.className("sc-brePNt"), "+ Add photo"));
        driver.findElement(By.id("imageUpload")).sendKeys(new File("test_data/right.jpg").getAbsolutePath());
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[contains(@class, 'sc-dIfARi')]//img"), 4));
        driver.findElement(By.xpath("//*[contains(@class, 'sc-dIfARi')]//img[1]")).click();
        driver.findElement(By.xpath("//button[text()='Done']")).click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button")));
        driver.findElement(By.id("next-button")).click();
        Thread.sleep(3000);
        driver.findElement(By.tagName("textarea")).sendKeys("test");
        driver.findElement(By.id("next-button")).click();
        driver.findElement(By.id("next-button")).click();
        driver.findElement(By.id("next-button")).click();


        Thread.sleep(5000);
        driver.quit();
    }
}

