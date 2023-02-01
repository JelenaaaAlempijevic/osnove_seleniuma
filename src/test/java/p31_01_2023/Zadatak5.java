package p31_01_2023;
//Napisati program koji:
//Ucitava stranicu https://cms.demo.katalon.com/
//Hvata  sve href atribute svih linkova iz navigacije
//Za svaki link proverava status da je veci ili jednak od 200 i manji od 400
//Koristan link za citanje status koda nekog url-a


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Zadatak5 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://cms.demo.katalon.com/");

        List<WebElement> navElement = driver.findElements(By.xpath("//*[@id='primary-menu']/ul/li/a"));
        for (int i = 0; i < navElement.size(); i++) {
            String url = navElement.get(i).getAttribute("href");
            try {
                int statusCode = new Helper().getHTTPResponseStatusCode(url);
                if (statusCode >= 200 && statusCode < 400) {
                    System.out.println("Link: " + url + " is available.");
                } else {
                    System.out.println("Link: " + url + " is not available.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        Thread.sleep(5000);
        driver.quit();
    }
}