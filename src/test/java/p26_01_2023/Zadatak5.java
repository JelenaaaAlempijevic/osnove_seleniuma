package p26_01_2023;
//5. Zadatak
//Napisati program koji ucitava stranicu https://www.ebay.com/ i bira kategoriju “Crafts”

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Zadatak5 {
    public static <WebElement> void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.ebay.com/");

        Select objSelect = new Select(driver.findElement(By.id("gh-cat")));
        objSelect.selectByVisibleText("Crafts");

        Thread.sleep(5000);
        driver.quit();


    }
}
