package p31_01_2023;
//Napisati program koji:
//Kreirati screenshots folder u projektu
//Ucitava stranicu https://cms.demo.katalon.com/
//Kreira screenshot stranice
//Sacuvati screenshot u folderu screenshots pod imenom screenshot1.jpg
//Koristan link 1. LAKSE CE VAM BITI PREKO OVOG
//Koristan link 2
//Koristan link 3



import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Zadatak4 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://cms.demo.katalon.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            new Helper()
                    .takeScreenshot(driver,
                            "screenshots/screenshot1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread.sleep(5000);
        driver.quit();
    }
}
