package p24_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UvodSelenium {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();


        driver.manage().window().maximize();
        driver.get("https://google.com");

        WebElement searchInput = driver.findElement(By.xpath("//input[@name='q']"));

        searchInput.sendKeys("IT Bootcamp");
        searchInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);


        driver.findElement(By.xpath("//a[@href ='https://itbootcamp.rs/']")).click();


        Thread.sleep(5000);
        driver.quit();



    }
}
