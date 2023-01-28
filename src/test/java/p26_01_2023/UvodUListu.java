package p26_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class UvodUListu {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://cms.demo.katalon.com/");

        List<WebElement> navLinks = driver
        .findElements(By.xpath("//*[@id='primary-menu']/ul/li/a"));

        for (int i = 0; i <navLinks.size() ; i++) {
            String txt = navLinks.get(i).getText();
            System.out.println(txt);

        }

        Thread.sleep(5000);
        driver.quit();



    }
}
