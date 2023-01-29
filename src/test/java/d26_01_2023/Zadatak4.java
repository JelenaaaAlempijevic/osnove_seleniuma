package d26_01_2023;
//4.Zadatak
//Napisati program koji ucitava stranicu https://geodata.solutions/
//Bira Country, State i City po vasoj zelji
//Pritom potrebno je izvrsiti cekanje da se povaje State-ovi nakon izbora Country-a
//I takodje je potrebno izvrsiti cekanje da se ucitaju gradovi nakon izbora State-a
//Izabrerit Country, State i City tako da imate podatke da selektujete!

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Zadatak4 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://geodata.solutions/");

        Select objSelect = new Select(driver.findElement(By.name("country")));
        objSelect.selectByValue("Serbia");
        Thread.sleep(1000);

        Select objSelect1 = new Select(driver.findElement(By.name("state")));
        objSelect.selectByValue("Central Serbia");
        Thread.sleep(1000);

        Select objSelect2 = new Select(driver.findElement(By.name("city")));
        objSelect.selectByValue("Nis");


        Thread.sleep(5000);
        driver.quit();



    }
}
