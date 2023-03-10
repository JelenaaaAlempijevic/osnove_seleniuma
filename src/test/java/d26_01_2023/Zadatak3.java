package d26_01_2023;
//3.Zad
//Napisati program koji:
//Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao sa stranice i ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
//POMOC: Brisite elemente odozdo.

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Zadatak3 {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://s.bootsnipp.com/iframe/Dq2X");

        List<WebElement> lista = driver.findElements(By.xpath("//div[@class='col-md-12']/div"));
        for (int i = lista.size()-1; i >= 0; i--) {
            lista.get(i).findElement(By.className("close")).click();
            Thread.sleep(500);
        }

        Thread.sleep(5000);
        driver.quit();
    }
}
