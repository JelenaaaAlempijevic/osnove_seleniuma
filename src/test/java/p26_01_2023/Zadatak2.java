package p26_01_2023;
//2.Zadatak
//Napisti program koji:
//Ucitava stranicu https://s.bootsnipp.com/iframe/z80en
//Hvata sve elemente prve kolone i stampa tekst svakog elementa. Kako da od nekog elementa procitamo tekst imate na sledecem linku
//Ceka 1s
//Hvata sve elemente prvog reda i stampa tekst svakog elementa
//Ceka 5s
//Zatvara pretrazivac
//Stampa treba da bude kao u primeru:
//John
//Mary
//July

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://s.bootsnipp.com/iframe/z80en");
        List<WebElement> kolona = driver.findElements(By.xpath("//div[@id='lorem']/table/tbody/tr/td[1]"));
        for (int i = 0; i < kolona.size(); i++) {
            System.out.println(kolona.get(i).getText());
        }

        Thread.sleep(1000);
        List<WebElement>prviRed = driver.findElements(By.xpath("//div[@id='lorem']//tr[1]/td"));
        for (int i = 0; i < prviRed.size(); i++) {
            System.out.print(prviRed.get(i).getText()+ " ");

        }


        Thread.sleep(5000);
        driver.quit();




    }
}
