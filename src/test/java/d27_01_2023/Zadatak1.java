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

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");


        List<WebElement> elements = driver.findElements(By.xpath("//*[@type='button']"));
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).findElement(By.linkText("//button[text()='Primary']")).click();
            Thread.sleep(1000);

        }


        driver.quit();

    }
}
