package p26_01_2023;
//6. Zadatak
//Ucitati stranicu https://cms.demo.katalon.com/
//Maksimizovati prozor
//Proveriri da li je je crno MENU dugme vidljivo (Ispisati poruke u konzoli)
//Prostavite duzinu prozora na 700px i visinu na 700px
//Proverite da li je crno MENU dugme vidljivo (Ispisati poruke u konzoli)

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak6 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://cms.demo.katalon.com/");
        driver.manage().window().maximize();

        driver.findElement(By.className("menu-toggle"));
        if(driver.findElement(By.className("menu-toggle")).isDisplayed()){
            System.out.println("MENU dugme je vidljivo.");
        } else {
            System.out.println("MENU dugme nije vidljivo.");
        }

        Thread.sleep(2000);


        driver.manage().window().setSize(new Dimension(700,700));
        Thread.sleep(3000);


        if(driver.findElement(By.className("menu-toggle")).isDisplayed()){
            System.out.println("MENU dugme je vidljivo.");
        } else {
            System.out.println("MENU dugme nije vidljivo.");
        }


        driver.quit();
    }
}
