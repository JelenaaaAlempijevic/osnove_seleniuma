package p27_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

    public class UvodUImplicitno {
        public static void main(String[] args) throws InterruptedException {

            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));


            driver.get("https://www.google.com/");
            driver.get("https://youtube.com/");



            System.out.println("NADJEN");


            Thread.sleep(5000);
            driver.quit();
        }
    }

