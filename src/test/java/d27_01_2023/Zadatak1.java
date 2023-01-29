package d27_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

//1.Zadatak
//Ucitati stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
//Klik na svako dugme od PRIMARY do DARK
//Sacekati da se toasts u desnom gornjem uglu pojavi
//Pauza izmedju klikova 1s
//Postavite implicitno cekanje za ucitavanje stranice i trazenje elemenata na 10s
public class Zadatak1 {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(1000);
        driver.manage().window().maximize();
        driver.get("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");
        Thread.sleep(1000);

        List<WebElement> elements = driver.findElements (By.xpath("//button[contains(@id, 'basic-')]"));
        for (int i = 0; i < elements.size(); i++) {
            Thread.sleep(2000);
            elements.get(i).click();
            Thread.sleep(500);

        }


        driver.quit();

    }
}
